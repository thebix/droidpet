package net.thebix.common.android.mvi

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

interface MviIntention
interface MviInitIntention

interface MviAction
interface MviResult
interface MviState

interface MviInteractor<Action : MviAction, Result : MviResult> {

    /**
     * to do the splitting, there is a publish method, we pass a function to it with observable as the parameter.
     * inside this publisher method we can do anything we want with the observable as long as at the end we just return one observable
     * .publish { shared ->
     *      Observable.merge(
     *          listOf(
     *              shared.ofType(MviAction.Init::class.java)
     *                  .compose(initProcessor)
     *           )
     *       )
     * }
     */
    fun actionProcessor(): ObservableTransformer<Action, Result>

}

abstract class MviViewModel<Intention : MviIntention, Action : MviAction, Result : MviResult, State : MviState>(
        private val interactor: MviInteractor<Action, Result>
) : ViewModel() {

    abstract val defaultState: State
    abstract val reducer: BiFunction<State, Result, State>
    abstract fun actionFromIntention(intent: Intention): Action

    fun states(): Observable<State> = statesObservable

    fun processIntentions(intentions: Observable<out Intention>): Disposable {
        return intentions
            .subscribe(
                { next -> intentionsSubject.onNext(next) },
                { error -> intentionsSubject.onError(error) }
            )
    }

    /**
     * Proxy subject used to keep the stream alive even after the UI gets recycled.
     * This is basically used to keep ongoing events and the last cached State alive
     * while the UI disconnects and reconnects on config changes.
     */
    private val intentionsSubject: PublishSubject<Intention> = PublishSubject.create()
    private val statesObservable: Observable<State> = compose()

    private fun compose(): Observable<State> {
        return intentionsSubject
            .subscribeOn(Schedulers.io())
            .compose(initIntentionFilter)
            .map(::actionFromIntention)
            .compose(interactor.actionProcessor())
            // Cache each state and pass it to the reducer to create a new state from
            // the previous cached one and the latest Result emitted from the action processor.
            // The Scan operator is used here for the caching.
            .scan(defaultState, reducer)
            // When a reducer just emits previousState, there's no reason to call render. In fact,
            // redrawing the UI in cases like this can cause jank (e.g. messing up snackbar animations
            // by showing the same snackbar twice in rapid succession).
            .distinctUntilChanged()
            // Emit the last one event of the stream on subscription
            // Useful when a View rebinds to the ViewModel after rotation.
            .replay(1)
            // Create the stream on creation without waiting for anyone to subscribe
            // This allows the stream to stay alive even when the UI disconnects and
            // match the stream's lifecycle to the ViewModel's one.
            .autoConnect(0)
    }

    /**
     * take only the first ever InitIntention and all intents of other types
     * to avoid reloading data on config changes
     */
    private val initIntentionFilter
        get() = ObservableTransformer<Intention, Intention> { shared ->
            Observable.merge(
                listOf(
                    shared.filter { it is MviInitIntention }.take(1),
                    shared.filter { it !is MviInitIntention }
                )
            )
        }
}
