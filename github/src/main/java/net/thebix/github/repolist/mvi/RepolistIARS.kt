// INFO: https://youtu.be/PXBXcHQeDLE

package net.thebix.github.repolist.mvi

import net.thebix.common_android.mvi.MviAction
import net.thebix.common_android.mvi.MviInitIntention
import net.thebix.common_android.mvi.MviIntention
import net.thebix.common_android.mvi.MviResult
import net.thebix.common_android.mvi.MviState
import net.thebix.github.api.models.Repo

sealed class RepolistIntention : MviIntention {

    object Init : RepolistIntention(),
                  MviInitIntention

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
