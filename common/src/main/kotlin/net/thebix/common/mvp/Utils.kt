package net.thebix.common.mvp

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

fun <T> Observable<T>.mvpUiSubscription(viewCallback: (Observable<T>) -> Disposable): Disposable {
    return viewCallback(this)
}

fun <T> Observable<T>.mvpBindUi(scheduler: Scheduler, updateView: (T) -> Unit): Disposable {
    return this
        .observeOn(scheduler)
        .subscribe(updateView)
}
