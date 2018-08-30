// INFO: https://youtu.be/PXBXcHQeDLE

package net.thebix.github.repolist.mvi

import net.thebix.github.api.models.Repo

// TODO: move interfaces to common module, mvi package
interface MviIntention

interface MviAction
interface MviResult
interface MviState

sealed class RepolistIntention : MviIntention {

    object Init : RepolistIntention()
    data class FetchRepos(val user: String) : RepolistIntention()

}

sealed class RepolistAction : MviAction {

    object Init : RepolistAction()
    data class FetchRepos(val user: String) : RepolistAction()

}

sealed class RepolistResult : MviResult {

    object Init : RepolistResult()
    object FetchReposStart : RepolistResult()
    object FetchReposError : RepolistResult()
    data class FetchReposFinish(
            val items: List<Repo>
    ) : RepolistResult()

}

data class RepolistState(

        val isReposFetching: Boolean = false,
        val items: List<Repo> = emptyList(),
        val error: String = ""
) : MviState
