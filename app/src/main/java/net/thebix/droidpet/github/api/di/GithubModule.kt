package net.thebix.droidpet.github.api.di

import dagger.Module
import dagger.Provides
import net.thebix.droidpet.github.api.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(
    includes = [
        OkHttpClientModule::class
    ]
)
class GithubModule {

    companion object {
        private const val URL_GITHUB_API = "https://api.github.com/"
    }

    @Provides
    @GithubScope
    fun provideGithubService(retrofit: Retrofit) =
        retrofit.create(GithubService::class.java)

    @Provides
    @GithubScope
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(URL_GITHUB_API)
            .build()

    @Provides
    @GithubScope
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()
}
