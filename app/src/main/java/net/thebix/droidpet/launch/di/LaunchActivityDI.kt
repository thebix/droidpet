package net.thebix.droidpet.launch.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import net.thebix.droidpet.launch.LaunchActivity
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.DroidpetComponentKey
import net.thebix.droidpet.di.DroidpetAppComponent
import net.thebix.droidpet.launch.developer.di.DeveloperComponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LaunchActivityScope

@LaunchActivityScope
@Component(
    dependencies = [
        DroidpetAppComponent::class
    ],
    modules = [
        LaunchActivityModule::class,
        DroidpetActivityBindingModule::class
    ]
)
interface LaunchActivityComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<LaunchActivityComponent> {
        fun appComponent(droidpetAppComponent: DroidpetAppComponent): Builder
        override fun build(): LaunchActivityComponent
    }

    fun inject(launchActivity: LaunchActivity)

}

@Module
interface LaunchActivityModule

@Module(
    subcomponents = [
        DeveloperComponent::class
    ]
)
abstract class DroidpetActivityBindingModule {

    @Suppress("unused")
    @Binds
    @IntoMap
    @DroidpetComponentKey(DeveloperComponent::class)
    abstract fun bindDeveloperComponent(developerComponentBuilder: DeveloperComponent.Builder): DroidpetComponentBuilder<*>
}
