package net.thebix.github

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import net.thebix.github.api.GithubService
import net.thebix.github.api.models.Repo
import net.thebix.github.di.GithubActivityScope
import javax.inject.Inject

@GithubActivityScope
class GithubRepository @Inject constructor(private val githubService: GithubService) {

    private val cachedRepos: HashMap<String, List<Repo>> = hashMapOf()
    private val publishSubject = PublishSubject.create<String>()

    fun getReposFromApi(user: String): Single<List<Repo>> {
        return githubService.repoList(user)
    }

    fun getRepos(user: String): Single<List<Repo>> {
        return Single.fromCallable { cachedRepos.getOrElse(user) { emptyList() } }
    }

    fun observeRepos(user: String): Observable<List<Repo>> {
        return publishSubject
            .filter { it == user }
            .switchMap { Observable.fromCallable { cachedRepos.getOrElse(user) { emptyList() } } }
    }

    fun putRepos(user: String, items: List<Repo>): Completable =
        Completable.fromCallable {
            cachedRepos[user] = items
            publishSubject.onNext(user)
        }
}
