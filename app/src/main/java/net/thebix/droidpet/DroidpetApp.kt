package net.thebix.droidpet

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import net.thebix.droidpet.di.DaggerDroidpetAppComponent
import timber.log.Timber

@Suppress("unused")
class DroidpetApp : Application() {

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

    private fun initDagger() {
        val droidpetAppComponent = DaggerDroidpetAppComponent.create()
        droidpetAppComponent.inject(this)
    }

    // endregion
}
