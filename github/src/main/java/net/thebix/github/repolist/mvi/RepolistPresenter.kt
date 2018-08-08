package net.thebix.github.repolist.mvi

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import net.thebix.github.repolist.di.RepolistScope
import net.thebix.github.repolist.mvp.Fetching
import net.thebix.github.usecase.FetchReposListUseCase
import javax.inject.Inject

@RepolistScope
class RepolistPresenter @Inject constructor(
        private val useCase: FetchReposListUseCase
) {
    // TODO: or Behaviour?
    private val intentionSubject = PublishSubject.create<RepolistIntention>()

    @Volatile
    private var prevState = RepolistState()

    fun pushIntention(intention: RepolistIntention) {
        intentionSubject.onNext(intention)
    }

    fun stateObserver(): Observable<RepolistState> {
        return intentionSubject.switchMap { intention ->
            when (intention) {
                RepolistIntention.Init -> Observable.fromCallable { prevState }
                is RepolistIntention.FetchRepos -> useCase.execute(intention.user)
                    // TODO: inject
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map { Fetching.End(it) as Fetching }
                    .startWith(Fetching.Start)
                    .onErrorReturn { Fetching.Error(it) }
                    .switchMap {
                        Observable.fromCallable {
                            when (it) {
                                is Fetching.Start -> prevState.copy(
                                    isReposFetching = true,
                                    error = ""
                                )
                                is Fetching.Error -> prevState.copy(
                                    isReposFetching = false,
                                    error = "Fetching error"
                                )
                                is Fetching.End -> prevState.copy(
                                    isReposFetching = false,
                                    items = it.items
                                )
                            }
                        }
                    }
            }
        }
    }

}
