package net.thebix.droidpet

import android.app.Application
import timber.log.Timber

class DroidpetApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
