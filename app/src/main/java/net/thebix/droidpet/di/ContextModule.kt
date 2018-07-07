package net.thebix.droidpet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(): Context =
        context.applicationContext

    @Provides
    fun provideActivityContext(): Context =
        context

}
