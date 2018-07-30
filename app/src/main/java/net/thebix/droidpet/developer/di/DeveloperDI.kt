package net.thebix.droidpet.developer.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.developer.DeveloperFragment
import net.thebix.droidpet.developer.navigation.DeveloperFragmentNavigator
import net.thebix.droidpet.navigation.NavigationManager
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
class DeveloperModule {

    @Provides
    @DeveloperScope
    fun provideDeveloperFragmentNavigator(navigationManager: NavigationManager): DeveloperFragmentNavigator = navigationManager

}
