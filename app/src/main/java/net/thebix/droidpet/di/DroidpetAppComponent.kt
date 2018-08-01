package net.thebix.droidpet.di

import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import net.thebix.common.DroidpetComponent
import net.thebix.droidpet.DroidpetApp
import net.thebix.droidpet.navigation.NavigatorHolder
import net.thebix.droidpet.navigation.NavigatorHolderImpl
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@ApplicationScope
@Component
    (
    modules = [
        ContextModule::class,
        DroidpetAppModule::class
    ]
)
interface DroidpetAppComponent : DroidpetComponent {

    fun inject(droidpetApp: DroidpetApp)

    @ApplicationContext
    fun getAppContext(): Context

    fun getNavigatorHolder(): NavigatorHolder

}

@Module
abstract class DroidpetAppModule {

    @Suppress("unused")
    @Binds
    @ApplicationScope
    abstract fun bindNavigatorHolder(navigatorImpl: NavigatorHolderImpl): NavigatorHolder

}
