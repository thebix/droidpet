package net.thebix.github.repolist.mvi

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import net.thebix.common.IoScheduler

// TODO: move interfaces to common module, mvi package
abstract class MviViewModel<Intention : MviIntention, State : MviState> : ViewModel() {

    // TODO: try return Disposable and manage it in Fragment
//    abstract fun processIntentions(intentions: Observable<out Intention>)
    abstract fun processIntentions(intentions: Observable<out Intention>): Disposable

    abstract fun states(): Observable<State>

}

class RepolistViewModel(
        private val interactor: RepolistInteractor,
        @IoScheduler private val ioScheduler: Scheduler
) : MviViewModel<RepolistIntention, RepolistState>() {

//    private val disposables = CompositeDisposable()
    /**
     * Proxy subject used to keep the stream alive even after the UI gets recycled.
     * This is basically used to keep ongoing events and the last cached State alive
     * while the UI disconnects and reconnects on config changes.
     */
    private val intentionsSubject: PublishSubject<RepolistIntention> = PublishSubject.create()
    private val statesObservable = compose()

    private val reducer
        get() = BiFunction<RepolistState, RepolistResult, RepolistState> { prevState, result ->
            when (result) {
                is RepolistResult.Init -> RepolistState()
                is RepolistResult.FetchReposStart -> prevState.copy(
                    isReposFetching = true,
                    error = ""
                )
                is RepolistResult.FetchReposFinish -> prevState.copy(
                    isReposFetching = false,
                    items = result.items
                )
                is RepolistResult.FetchReposError -> prevState.copy(
                    isReposFetching = false,
                    error = "Error while loading"
                )
            }
        }

    /**
     * take only the first ever InitIntention and all intents of other types
     * to avoid reloading data on config changes
     */
    private val initIntentionFilter
        get() = ObservableTransformer<RepolistIntention, RepolistIntention> { shared ->
            Observable.merge(
                listOf(
                    shared.ofType(RepolistIntention.Init::class.java).take(1),
                    shared.filter { it -> it !is RepolistIntention.Init }
                )
            )
        }

    override fun processIntentions(intentions: Observable<out RepolistIntention>): Disposable {
//    override fun processIntentions(intentions: Observable<out RepolistIntention>) {
        return intentions
            .subscribe(
                { next -> intentionsSubject.onNext(next) },
                { error -> intentionsSubject.onError(error) }
            )
//        disposables.add(
//            intentions
//                .subscribe(
//                    { next -> intentionsSubject.onNext(next) },
//                    { error -> intentionsSubject.onError(error) }
//                )
//        )
    }

    override fun states(): Observable<RepolistState> = statesObservable

    private fun compose(): Observable<RepolistState> =
        intentionsSubject
            .subscribeOn(ioScheduler)
            .compose(initIntentionFilter)
            .map(::actionFromIntention)
            .compose(interactor.actionProcessor)
            .scan(RepolistState(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)

    private fun actionFromIntention(intent: RepolistIntention): RepolistAction =
        when (intent) {
            is RepolistIntention.Init -> RepolistAction.Init
            is RepolistIntention.FetchRepos -> RepolistAction.FetchRepos(intent.user)
        }

//    fun dispose() {
//        disposables.clear()
//    }
}
