package net.thebix.droidpet

import com.squareup.leakcanary.LeakCanary
import net.thebix.common.di.CommonComponent
import net.thebix.common.android.CommonAndroidComponent
import net.thebix.common.android.CommonAndroidModule
import net.thebix.common.android.DaggerCommonAndroidComponent
import net.thebix.common.di.DaggerCommonComponent
import net.thebix.common.android.DroidpetAppBase
import net.thebix.droidpet.di.DaggerDroidpetAppComponent
import net.thebix.droidpet.di.DroidpetAppComponent
import net.thebix.droidpet.navigation.NavigationManagerImpl
import timber.log.Timber

class DroidpetApp : DroidpetAppBase() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initDagger()
    }

    // region Dagger

    private val droidpetAppComponent by lazy<DroidpetAppComponent> {
        DaggerDroidpetAppComponent.create()
    }

    private var commonComponent: CommonComponent? = null
    private var commonAndroidComponent: CommonAndroidComponent? = null

    override fun getCommonComponent(): CommonComponent {
        if (commonComponent == null) {
            commonComponent = DaggerCommonComponent.create()
        }
        return commonComponent!!
    }

    override fun getCommonAndroidComponent(): CommonAndroidComponent {
        if (commonAndroidComponent == null) {
            commonAndroidComponent = DaggerCommonAndroidComponent.builder()
                .commonAndroidModule(
                    CommonAndroidModule(
                        applicationContext,
                        NavigationManagerImpl(applicationContext)
                    )
                )
                .build()
        }
        return commonAndroidComponent!!
    }

    private fun initDagger() {
        droidpetAppComponent.inject(this)
    }

    // endregion
}
