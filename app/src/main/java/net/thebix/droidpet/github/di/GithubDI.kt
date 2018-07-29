package net.thebix.droidpet.github.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.DroidpetComponentKey
import net.thebix.droidpet.github.GithubActivity
import net.thebix.droidpet.github.api.GithubService
import net.thebix.droidpet.github.repolist.di.RepolistComponent
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
        GithubModule::class,
        GithubBindingModule::class,
        GithubApiModule::class
    ]
)
interface GithubComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<GithubComponent> {
        fun networkComponent(networkComponent: NetworkComponent): Builder
        override fun build(): GithubComponent
    }

    fun inject(githubActivity: GithubActivity)

}

@Module
interface GithubModule

@Module(
    subcomponents = [
        RepolistComponent::class
    ]
)
abstract class GithubBindingModule {

    @Suppress("unused")
    @Binds
    @IntoMap
    @DroidpetComponentKey(RepolistComponent::class)
    abstract fun bindRepolistComponent(repolistComponentBuilder: RepolistComponent.Builder): DroidpetComponentBuilder<*>
}

@Module
class GithubApiModule {

    companion object {
        private const val URL_GITHUB_API = "https://api.github.com/"
    }

    @Provides
    @GithubScope
    fun provideGithubService(retrofitBuilder: Retrofit.Builder): GithubService =
        retrofitBuilder
            .baseUrl(URL_GITHUB_API)
            .build()
            .create(GithubService::class.java)

}
