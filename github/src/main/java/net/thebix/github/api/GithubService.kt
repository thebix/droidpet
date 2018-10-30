package net.thebix.github.api

import io.reactivex.Single
import net.thebix.github.api.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{user}/repos")
    fun repoList(@Path("user") user: String): Single<List<Repo>>

}
