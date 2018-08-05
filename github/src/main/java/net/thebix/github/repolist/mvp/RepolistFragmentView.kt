package net.thebix.github.repolist.mvp

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import net.thebix.github.api.models.Repo


interface RepolistFragmentView {

    fun getReposList(): Observable<List<Repo>>

    fun showReposListFetchStart(): (Observable<Fetching.Start>) -> Disposable
    fun showReposListFetchError(): (Observable<Fetching.Error>) -> Disposable
    fun showReposListFetchEnd(): (Observable<Fetching.End>) -> Disposable

}
