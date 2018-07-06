package net.thebix.droidpet.github.repolist.di

import dagger.Component
import dagger.Module
import net.thebix.droidpet.github.api.GithubService
import net.thebix.droidpet.github.api.di.GithubComponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepolistScope

@RepolistScope
@Component(
    dependencies = [
        GithubComponent::class
    ],
    modules = [
        RepolistModule::class
    ]
)
interface RepolistComponent {

    fun getGithubService(): GithubService

}

@Module
class RepolistModule {

    // TODO: add fragment related providers/binds

}
