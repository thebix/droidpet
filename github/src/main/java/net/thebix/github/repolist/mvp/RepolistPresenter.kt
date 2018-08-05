package net.thebix.github.repolist.mvp

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import net.thebix.common.IoScheduler
import net.thebix.common.mvp.mvpUiSubscription
import net.thebix.github.api.models.Repo
import net.thebix.github.repolist.di.RepolistScope
import javax.inject.Inject

@RepolistScope
class RepolistPresenter @Inject constructor(@IoScheduler private val ioScheduler: Scheduler) {

    private val disposables = CompositeDisposable()

    fun bind(view: RepolistFragmentView): CompositeDisposable {
        val repolistObservable = view.getReposList()
            .subscribeOn(ioScheduler)
            .switchMap {
                Observable.fromCallable { Fetching.End(it) as Fetching }
            }
            .onErrorReturn { Fetching.Error(it) }
            .startWith(Fetching.Start)
            .share()

        disposables.addAll(
            repolistObservable.ofType(Fetching.Start::class.java)
                .mvpUiSubscription(view.showReposListFetchStart()),
            repolistObservable.ofType(Fetching.Error::class.java)
                .mvpUiSubscription(view.showReposListFetchError()),
            repolistObservable.ofType(Fetching.End::class.java)
                .mvpUiSubscription(view.showReposListFetchEnd())
        )
        return disposables
    }

    fun dispose() {
        disposables.clear()
    }
}

// TODO: refactor to generic and move to common module
sealed class Fetching {

    object Start : Fetching()
    data class Error(val throwable: Throwable) : Fetching()
    data class End(val items: List<Repo>) : Fetching()
}
