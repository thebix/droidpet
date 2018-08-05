package net.thebix.github.api

import io.reactivex.Observable
import net.thebix.github.api.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{user}/repos")
    fun repoList(@Path("user") user: String): Observable<List<Repo>>

}
