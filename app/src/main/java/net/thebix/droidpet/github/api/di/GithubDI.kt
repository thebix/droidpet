package net.thebix.droidpet.github.api.di

import dagger.Component
import dagger.Module
import dagger.Provides
import net.thebix.droidpet.di.DroidpetComponent
import net.thebix.droidpet.github.api.GithubService
import net.thebix.droidpet.network.di.NetworkComponent
import retrofit2.Retrofit
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GithubScope

@GithubScope
@Component(
    dependencies = [
        NetworkComponent::class
    ],
    modules = [
        GithubModule::class
    ]
)
interface GithubComponent : DroidpetComponent {

    fun getGithubService(): GithubService
}


@Module
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
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder) =
        retrofitBuilder
            .baseUrl(URL_GITHUB_API)
            .build()
}
