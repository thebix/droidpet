package net.thebix.droidpet

import android.app.Activity
import android.app.Application
import net.thebix.droidpet.github.api.di.GithubComponent
import timber.log.Timber

@Suppress("unused")
class DroidpetApp : Application() {

    companion object {
        fun get(activity: Activity): DroidpetApp = activity.application as DroidpetApp
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
