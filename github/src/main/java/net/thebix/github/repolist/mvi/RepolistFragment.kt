package net.thebix.github.repolist.mvi

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import net.thebix.common_android.DroidpetActivity
import net.thebix.github.R
import net.thebix.github.repolist.di.RepolistComponent
import javax.inject.Inject

class RepolistFragment : Fragment() {

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
        disposables.addAll(
            presenter.stateObserver()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::render),
            RxView.clicks(searchButton).map { "thebix" }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { presenter.pushIntention(RepolistIntention.FetchRepos(it)) }
        )
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    @UiThread
    private fun render(state: RepolistState) {
        with(state) {
            progressView.visibility = if (isReposFetching) VISIBLE else GONE
            when {
                error.isNotBlank() -> repolistItems.text = error
                items.isNotEmpty() -> repolistItems.text = items.joinToString("\n") { it.name }
            }
        }
    }
}
