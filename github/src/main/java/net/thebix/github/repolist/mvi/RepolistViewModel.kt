package net.thebix.github.repolist.mvi

import io.reactivex.functions.BiFunction
import net.thebix.common_android.mvi.MviViewModel

class RepolistViewModel(
        interactor: RepolistInteractor
) : MviViewModel<RepolistIntention, RepolistAction, RepolistResult, RepolistState>(interactor) {

    override val defaultState: RepolistState get() = RepolistState()

    override val reducer
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

    override fun actionFromIntention(intent: RepolistIntention): RepolistAction =
        when (intent) {
            is RepolistIntention.Init -> RepolistAction.Init
            is RepolistIntention.FetchRepos -> RepolistAction.FetchRepos(intent.user)
        }
}
