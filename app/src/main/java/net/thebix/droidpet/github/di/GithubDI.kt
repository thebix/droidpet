package net.thebix.droidpet.github.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.DroidpetComponentKey
import net.thebix.droidpet.github.GithubActivity
import net.thebix.droidpet.github.api.di.GithubApiComponent
import net.thebix.droidpet.github.repolist.di.RepolistComponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GithubScope

@GithubScope
@Component(
    dependencies = [
        // TODO: remove component, use module
        GithubApiComponent::class
    ],
    modules = [
        GithubModule::class,
        GithubBindingModule::class
    ]
)
interface GithubComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<GithubComponent> {
        fun githubApiComponent(githubApiComponent: GithubApiComponent): DroidpetComponentBuilder<GithubComponent>
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
