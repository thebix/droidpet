package net.thebix.github.usecase

import io.reactivex.Single
import net.thebix.github.api.GithubService
import net.thebix.github.api.models.Repo
import net.thebix.github.di.GithubActivityScope
import net.thebix.github.repolist.di.RepolistScope
import javax.inject.Inject

@RepolistScope
class FetchReposListUseCase @Inject constructor(private val githubService: GithubService) {

    fun execute(user: String): Single<List<Repo>> {
        return githubService.repoList(user)
            .single(emptyList())
    }
}
