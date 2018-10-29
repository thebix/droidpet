package net.thebix.common.android

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import net.thebix.common.ApplicationContext
import net.thebix.common.IoScheduler
import net.thebix.common.di.ApplicationScope
import net.thebix.common.di.CommonModule

@ApplicationScope
@Component
    (
    modules = [
        CommonModule::class,
        CommonAndroidModule::class
    ]
)
interface CommonAndroidComponent {

    @ApplicationContext
    fun getAppContext(): Context

    fun getNavigationManager(): NavigationManager

    @IoScheduler
    fun getIoScheduler(): Scheduler
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
