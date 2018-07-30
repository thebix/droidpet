package net.thebix.droidpet.di

import android.content.Context
import dagger.Component
import dagger.Module
import net.thebix.droidpet.DroidpetApp
import net.thebix.droidpet.common.DroidpetComponent
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

}

@Module
interface DroidpetAppModule
