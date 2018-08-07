package net.thebix.github.repolist.mvi

import net.thebix.github.api.models.Repo

sealed class RepolistIntention {

    object Init : RepolistIntention()
    data class FetchRepos(val user: String) : RepolistIntention()

}

data class RepolistState(

        val isReposFetching: Boolean = false,
        val items: List<Repo> = emptyList(),
        val error: String = ""
)
