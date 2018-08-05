package net.thebix.github.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import net.thebix.common.DroidpetComponent
import net.thebix.common.DroidpetComponentBuilder
import net.thebix.common.DroidpetComponentKey
import net.thebix.common.di.CommonComponent
import net.thebix.github.GithubActivity
import net.thebix.github.api.GithubService
import net.thebix.github.repolist.di.RepolistComponent
import net.thebix.network.di.NetworkComponent
import retrofit2.Retrofit
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GithubActivityScope

@GithubActivityScope
@Component(
    dependencies = [
        CommonComponent::class,
        NetworkComponent::class
    ],
    modules = [
        GithubActivityModule::class,
        GithubBindingModule::class,
        GithubApiModule::class
    ]
)
interface GithubActivityComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<GithubActivityComponent> {
        fun commonComponent(commonComponent: CommonComponent): Builder
        fun networkComponent(networkComponent: NetworkComponent): Builder
        override fun build(): GithubActivityComponent
    }

    fun inject(githubActivity: GithubActivity)

}

@Module
interface GithubActivityModule

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
    @GithubActivityScope
    fun provideGithubService(retrofitBuilder: Retrofit.Builder): GithubService =
        retrofitBuilder
            .baseUrl(URL_GITHUB_API)
            .build()
            .create(GithubService::class.java)

}
