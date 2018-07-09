package net.thebix.droidpet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Deprecated("this module is definitely shouldn't be used")
@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(): Context =
        context.applicationContext

    @Provides
    fun provideActivityContext(): Context =
        context

}
