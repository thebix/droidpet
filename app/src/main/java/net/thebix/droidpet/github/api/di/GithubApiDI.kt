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
annotation class GithubApiScope

@GithubApiScope
@Component(
    dependencies = [
        NetworkComponent::class
    ],
    modules = [
        GithubApiModule::class
    ]
)
// TODO: probably this module doesn't need to be related to DroidpetComponent
interface GithubApiComponent : DroidpetComponent {

    fun getGithubService(): GithubService
}


@Module
class GithubApiModule {

    companion object {
        private const val URL_GITHUB_API = "https://api.github.com/"
    }

    @Provides
    @GithubApiScope
    fun provideGithubService(retrofit: Retrofit) =
        retrofit.create(GithubService::class.java)

    @Provides
    @GithubApiScope
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder) =
        retrofitBuilder
            .baseUrl(URL_GITHUB_API)
            .build()
}
