package net.thebix.github.repolist.di

import dagger.Module
import dagger.Subcomponent
import net.thebix.common.DroidpetComponent
import net.thebix.common.DroidpetComponentBuilder
import net.thebix.github.repolist.RepolistFragment
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepolistScope

@RepolistScope
@Subcomponent(
    modules = [
        RepolistModule::class
    ]
)
interface RepolistComponent : DroidpetComponent {

    @Subcomponent.Builder
    interface Builder : DroidpetComponentBuilder<RepolistComponent> {
        override fun build(): RepolistComponent
    }

    fun inject(repolistFragment: RepolistFragment)
    fun inject(repolistFragment: net.thebix.github.repolist.mvi.RepolistFragment)

}

@Module
interface RepolistModule
