package net.thebix.common_android

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import net.thebix.common.ApplicationContext
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@ApplicationScope
@Component
    (
    modules = [
        CommonModule::class
    ]
)
interface CommonComponent {

    @ApplicationContext
    fun getAppContext(): Context

    fun getNavigationManager(): NavigationManager
}

@Module
class CommonModule(
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
