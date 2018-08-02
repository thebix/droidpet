package net.thebix.common_android

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import net.thebix.common.ApplicationContext
import net.thebix.common.DroidpetComponent
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

    fun getNavigatorHolder(): NavigatorHolder

}

@Module
class CommonModule(private val appContext: Context) {


    // TODO: try Binds
    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(): Context =
        appContext.applicationContext

    @Suppress("unused")
    @Provides
    @ApplicationScope
    fun provideNavigatorHolder(): NavigatorHolder = NavigatorHolderImpl()

}
