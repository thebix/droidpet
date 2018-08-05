package net.thebix.droidpet.di

import dagger.Component
import dagger.Module
import net.thebix.common.DroidpetComponent
import net.thebix.common.di.ApplicationScope
import net.thebix.droidpet.DroidpetApp

@ApplicationScope
@Component
    (
    modules = [
        DroidpetAppModule::class
    ]
)
interface DroidpetAppComponent : DroidpetComponent {

    fun inject(droidpetApp: DroidpetApp)

}

@Module
interface DroidpetAppModule
