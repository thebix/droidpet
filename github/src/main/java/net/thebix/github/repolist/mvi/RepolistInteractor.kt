package net.thebix.github.repolist.mvi

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import net.thebix.common.IoScheduler
import net.thebix.common_android.mvi.MviInteractor
import net.thebix.github.repolist.di.RepolistScope
import net.thebix.github.usecase.FetchReposListUseCase
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@RepolistScope
class RepolistInteractor @Inject constructor(
        private val fetchReposListUseCase: FetchReposListUseCase,
        @IoScheduler private val ioScheduler: Scheduler
) : MviInteractor<RepolistAction, RepolistResult> {

    private val initProcessor =
        ObservableTransformer<RepolistAction, RepolistResult> { actions ->
            actions
                .switchMap {
                    Observable.fromCallable { RepolistResult.Init }
                }
        }

    private val fetchReposProcessor =
        ObservableTransformer<RepolistAction.FetchRepos, RepolistResult> { actions ->
            actions
                .switchMap { action ->
                    fetchReposListUseCase.execute(action.user)
                        .subscribeOn(ioScheduler)
                        .toObservable()
                        .switchMap { items ->
                            Observable.fromCallable { RepolistResult.FetchReposFinish(items) as RepolistResult }
                        }
                        .onErrorReturn {
                            if (it !is IOException) {
                                Timber.e(it)
                            }
                            RepolistResult.FetchReposError
                        }
                        .startWith(RepolistResult.FetchReposStart)
                }
        }

    override fun actionProcessor(): ObservableTransformer<RepolistAction, RepolistResult> =
        ObservableTransformer { actions: Observable<RepolistAction> ->
            actions
                /**
                 * to do the splitting, there is a publish method, we pass a function to it with observable as the parameter.
                 * inside this publisher method we can do anything we want with the observable as long as at the end we just return one observable
                 */
                .publish { shared ->
                    Observable.merge(
                        listOf(
                            shared.ofType(RepolistAction.Init::class.java)
                                .compose(initProcessor),
                            shared.ofType(RepolistAction.FetchRepos::class.java)
                                .compose(fetchReposProcessor)
                        )
                    )
                }
        }

}
