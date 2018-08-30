package net.thebix.github.repolist.mvi

import android.arch.lifecycle.ViewModelProvider
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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.thebix.common_android.DroidpetActivity
import net.thebix.common_android.bindView
import net.thebix.github.R
import net.thebix.github.ViewModelFactory
import net.thebix.github.repolist.di.RepolistComponent
import timber.log.Timber
import javax.inject.Inject

class RepolistFragment : Fragment() {

    companion object {
        fun newInstance() = RepolistFragment()
    }

    private val repolistItems by bindView<TextView>(R.id.fragment_github_repolist_items)
    private val searchButton by bindView<View>(R.id.github_repolist_search_button)
    private val progressView by bindView<View>(R.id.fragment_repolist_items)

    @Inject
    lateinit var interactor: RepolistInteractor
    private val viewModel: RepolistViewModel by lazy(LazyThreadSafetyMode.NONE) {
        // TODO: inject Scheduler
        ViewModelProvider(this, ViewModelFactory(interactor, Schedulers.io()))
            .get(RepolistViewModel::class.java)
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        val repolistComponent = (activity as DroidpetActivity).getDroidpetComponentBuilder(RepolistComponent::class.java)
            .build() as RepolistComponent
        repolistComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_github_repolist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.processIntentions(intentions())
    }

    override fun onStart() {
        super.onStart()
        disposables.addAll(
            viewModel.states()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::render)
            ,viewModel.processIntentions(intentions())

        )
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun onDestroy() {
//        viewModel.dispose()
        super.onDestroy()
    }

    @UiThread
    private fun render(state: RepolistState) {
        Timber.d("State: $state")
        with(state) {
            progressView.visibility = if (isReposFetching) VISIBLE else GONE
            when {
                error.isNotBlank() -> repolistItems.text = error
                items.isNotEmpty() -> repolistItems.text = items.joinToString("\n") { it.name }
            }
        }
    }

    private fun intentions(): Observable<RepolistIntention> = Observable.merge(
        listOf(
            Observable.fromCallable { RepolistIntention.Init },
            RxView.clicks(searchButton).map { "thebix" }
                .map { RepolistIntention.FetchRepos(it) }
        )
    )
}
