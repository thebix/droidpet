package net.thebix.droidpet.launch.developer.di

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.launch.developer.DeveloperFragment
import net.thebix.droidpet.launch.developer.navigation.DeveloperFragmentNavigator
import net.thebix.droidpet.launch.developer.navigation.DeveloperFragmentNavigatorImpl
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DeveloperScope

@DeveloperScope
@Subcomponent(
    modules = [
        DeveloperModule::class
    ]
)
interface DeveloperComponent : DroidpetComponent {

    @Subcomponent.Builder
    interface Builder : DroidpetComponentBuilder<DeveloperComponent> {
        override fun build(): DeveloperComponent
    }

    fun inject(developerFragment: DeveloperFragment)

}

@Module
abstract class DeveloperModule {

    @Suppress("unused")
    @Binds
    @DeveloperScope
    abstract fun bindDeveloperFragmentNavigator(developerFragmentNavigator: DeveloperFragmentNavigatorImpl): DeveloperFragmentNavigator

}
