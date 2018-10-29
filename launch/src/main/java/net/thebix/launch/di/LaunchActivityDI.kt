package net.thebix.launch.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import net.thebix.common.DroidpetComponent
import net.thebix.common.DroidpetComponentBuilder
import net.thebix.common.DroidpetComponentKey
import net.thebix.common.android.CommonAndroidComponent
import net.thebix.launch.LaunchActivity
import net.thebix.launch.developer.di.DeveloperComponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LaunchActivityScope

@LaunchActivityScope
@Component(
    dependencies = [
        CommonAndroidComponent::class
    ],
    modules = [
        LaunchActivityModule::class,
        DroidpetActivityBindingModule::class
    ]
)
interface LaunchActivityComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<LaunchActivityComponent> {
        fun commonAndroidComponent(commonAndroidComponent: CommonAndroidComponent): Builder
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
