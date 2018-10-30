package net.thebix.github.repolist.mvi

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import net.thebix.common.IoScheduler
import net.thebix.common.android.mvi.MviInteractor
import net.thebix.github.GithubRepository
import net.thebix.github.repolist.di.RepolistScope
import net.thebix.github.usecase.FetchReposUseCase
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@RepolistScope
class RepolistInteractor @Inject constructor(
        private val fetchReposUseCase: FetchReposUseCase,
        @IoScheduler private val ioScheduler: Scheduler,
        private val githubRepository: GithubRepository
) : MviInteractor<RepolistAction, RepolistResult> {

    private val initProcessor =
        ObservableTransformer<RepolistAction, RepolistResult> { actions ->
            actions
                .switchMap {
                    Observable.fromCallable { RepolistResult.Init }
                }
        }

    private val dataProcessor =
        ObservableTransformer<RepolistAction.FetchRepos, RepolistResult> { actions ->
            actions
                .distinctUntilChanged { t1, t2 -> t1.user != t2.user }
                .switchMap { action ->
                    githubRepository.observeRepos(action.user)
                        .switchMap { items ->
                            Observable.fromCallable { RepolistResult.Data(items) }
                        }
                }
        }

    private val fetchReposProcessor =
        ObservableTransformer<RepolistAction.FetchRepos, RepolistResult> { actions ->
            actions
                .switchMap { action ->
                    fetchReposUseCase.execute(action.user)
                        .subscribeOn(ioScheduler)
                        .toSingleDefault(RepolistResult.FetchReposFinish as RepolistResult)
                        .onErrorReturn {
                            if (it !is IOException) {
                                Timber.e(it)
                            }
                            RepolistResult.FetchReposError
                        }
                        .toObservable()
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
                                .compose(fetchReposProcessor),
                            shared.ofType(RepolistAction.FetchRepos::class.java)
                                .compose(dataProcessor)
                        )
                    )
                }
        }

}
