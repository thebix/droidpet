package net.thebix.droidpet

import com.squareup.leakcanary.LeakCanary
import net.thebix.common_android.CommonComponent
import net.thebix.common_android.CommonModule
import net.thebix.common_android.DaggerCommonComponent
import net.thebix.common_android.DroidpetAppBase
import net.thebix.droidpet.di.DaggerDroidpetAppComponent
import net.thebix.droidpet.di.DroidpetAppComponent
import timber.log.Timber

@Suppress("unused")
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

    override fun getCommonComponent(): CommonComponent {
        if (commonComponent == null) {
            commonComponent = DaggerCommonComponent.builder()
                .commonModule(CommonModule(applicationContext))
                .build()
        }
        return commonComponent!!
    }

    private fun initDagger() {
        droidpetAppComponent.inject(this)
    }

    // endregion
}
