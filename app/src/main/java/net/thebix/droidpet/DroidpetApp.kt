package net.thebix.droidpet

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import net.thebix.droidpet.di.ContextModule
import net.thebix.droidpet.di.DaggerDroidpetAppComponent
import net.thebix.droidpet.di.DroidpetAppComponent
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

    val droidpetAppComponent by lazy<DroidpetAppComponent> {
        DaggerDroidpetAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    private fun initDagger() {
        droidpetAppComponent.inject(this)
    }

    // endregion
}
