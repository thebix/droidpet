package net.thebix.github.repolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import net.thebix.common.mvp.mvpBindUi
import net.thebix.common_android.DroidpetActivity
import net.thebix.github.R
import net.thebix.github.repolist.di.RepolistComponent
import net.thebix.github.repolist.mvp.Fetching
import net.thebix.github.repolist.mvp.RepolistFragmentView
import net.thebix.github.repolist.mvp.RepolistPresenter
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RepolistFragment : Fragment(),
                         RepolistFragmentView {

    companion object {
        fun newInstance() = RepolistFragment()
    }

    private val repolistItems get() = view!!.findViewById(R.id.fragment_github_repolist_items) as TextView
    private val searchButton get() = view!!.findViewById(R.id.github_repolist_search_button) as View
    private val progressView get() = view!!.findViewById(R.id.fragment_repolist_items) as View

    @Inject
    lateinit var presenter: RepolistPresenter

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repolistComponent = (activity as DroidpetActivity).getDroidpetComponentBuilder(RepolistComponent::class.java)
            .build() as RepolistComponent
        repolistComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_github_repolist, container, false)

    override fun onStart() {
        super.onStart()
        disposables.add(
            presenter.bind(this)
        )
    }

    override fun onStop() {
        presenter.dispose()
        super.onStop()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    override fun fetchReposListByUser(): Observable<String> {
        return RxView.clicks(searchButton).map { "thebix" }
    }

    override fun showReposListFetchStart(): (Observable<Fetching.Start>) -> Disposable {
        return { observable ->
            observable.mvpBindUi(AndroidSchedulers.mainThread()) {
                Timber.d("Fetching started")
                progressView.visibility = VISIBLE
            }
        }
    }

    override fun showReposListFetchError(): (Observable<Fetching.Error>) -> Disposable {
        return { observable ->
            observable.mvpBindUi(AndroidSchedulers.mainThread()) {
                progressView.visibility = GONE
                if (it.throwable !is IOException) {
                    Timber.e(it.throwable)
                }
                repolistItems.text = "Fetching error"
            }
        }
    }

    override fun showReposListFetchEnd(): (Observable<Fetching.End>) -> Disposable {
        return { observable ->
            observable.mvpBindUi(AndroidSchedulers.mainThread()) { fetchingEnd ->
                progressView.visibility = GONE
                val repos = fetchingEnd.items
                Timber.d("Received the list of repos, count: <${repos.size}>")
                if (repos.isNotEmpty()) {
                    repolistItems.text = repos.joinToString("\n") { it.name }
                }
            }
        }
    }
}
