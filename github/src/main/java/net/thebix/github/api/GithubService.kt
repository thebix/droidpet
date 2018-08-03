package net.thebix.github.api

import net.thebix.github.api.models.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{user}/repos")
    fun repoList(@Path("user") user: String): Call<List<Repo>>

}
