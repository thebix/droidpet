package net.thebix.droidpet.di

import javax.inject.Qualifier

@Deprecated("get app context related things another way")
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
