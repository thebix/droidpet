package net.thebix.github.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import net.thebix.common.IoScheduler
import net.thebix.github.GithubRepository
import net.thebix.github.di.GithubActivityScope
import javax.inject.Inject

@GithubActivityScope
class FetchReposUseCase @Inject constructor(
        @IoScheduler private val ioScheduler: Scheduler,
        private val githubRepository: GithubRepository
) {

    fun execute(user: String): Completable {
        return githubRepository.getReposFromApi(user)
            .subscribeOn(ioScheduler)
            .flatMapCompletable {
                githubRepository.putRepos(user, it)
            }
    }
}
