package net.thebix.droidpet.github.api.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @GithubScope
    @ApplicationContext
    fun provideApplicationContext(): Context =
        context.applicationContext

    @Provides
    @GithubScope
    fun provideActivityContext(): Context =
        context

}
