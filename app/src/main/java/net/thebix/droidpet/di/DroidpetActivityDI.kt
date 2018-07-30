package net.thebix.droidpet.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import net.thebix.droidpet.DroidpetActivity
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.DroidpetComponentKey
import net.thebix.droidpet.developer.di.DeveloperComponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DroidpetActivityScope

@DroidpetActivityScope
@Component(
    dependencies = [
        DroidpetAppComponent::class
    ],
    modules = [
        DroidpetActivityModule::class,
        DroidpetActivityBindingModule::class
    ]
)
interface DroidpetActivityComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<DroidpetActivityComponent> {
        fun appComponent(droidpetAppComponent: DroidpetAppComponent): Builder
        override fun build(): DroidpetActivityComponent
    }

    fun inject(droidpetActivity: DroidpetActivity)

}

@Module
interface DroidpetActivityModule

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
    abstract fun bindDroidpetActivityComponent(droidpetActivityComponentBuilder: DeveloperComponent.Builder): DroidpetComponentBuilder<*>
}
