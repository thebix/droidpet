package net.thebix.droidpet.di

import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Singleton
@Component
interface DroidpetAppComponent : DroidpetComponent
