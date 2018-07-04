package net.thebix.droidpet.github.api.di

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GithubScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
