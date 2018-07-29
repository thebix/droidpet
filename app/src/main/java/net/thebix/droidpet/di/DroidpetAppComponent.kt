package net.thebix.droidpet.di

import dagger.Component
import net.thebix.droidpet.DroidpetApp
import net.thebix.droidpet.common.DroidpetComponent
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Singleton
@Component
interface DroidpetAppComponent : DroidpetComponent {

    fun inject(droidpetApp: DroidpetApp)

}
