package net.thebix.droidpet.github.repolist.di

import dagger.Component
import dagger.Module
import net.thebix.droidpet.di.DroidpetComponent
import net.thebix.droidpet.github.api.di.GithubApiComponent
import net.thebix.droidpet.github.repolist.RepolistFragment
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepolistScope

@RepolistScope
@Component(
    dependencies = [
        GithubApiComponent::class
    ],
    modules = [
        RepolistModule::class
    ]
)
interface RepolistComponent : DroidpetComponent {

    fun inject(repolistFragment: RepolistFragment)

}

@Module
class RepolistModule {

    // TODO: add fragment related providers/binds

}
