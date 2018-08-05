package net.thebix.common_android

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import net.thebix.common.ApplicationContext
import net.thebix.common.di.ApplicationScope

@ApplicationScope
@Component
    (
    modules = [
        CommonAndroidModule::class
    ]
)
interface CommonAndroidComponent {

    @ApplicationContext
    fun getAppContext(): Context

    fun getNavigationManager(): NavigationManager
}

@Module
class CommonAndroidModule(
        private val appContext: Context,
        private val navigationManager: NavigationManager
) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(): Context =
        appContext.applicationContext


    @Provides
    @ApplicationScope
    fun provideNavigationManager() = navigationManager

}
