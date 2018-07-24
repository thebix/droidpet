package net.thebix.droidpet

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import net.thebix.droidpet.di.DaggerDroidpetAppComponent
import net.thebix.droidpet.di.DroidpetAppComponent
import net.thebix.droidpet.di.DroidpetComponent
import net.thebix.droidpet.github.api.di.DaggerGithubApiComponent
import net.thebix.droidpet.github.api.di.GithubApiComponent
import net.thebix.droidpet.network.di.DaggerNetworkComponent
import net.thebix.droidpet.network.di.NetworkComponent
import timber.log.Timber

@Suppress("unused")
class DroidpetApp : Application() {

    companion object {
        fun get(activity: Activity): DroidpetApp = activity.application as DroidpetApp
    }

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

    // TODO: move to separate class
    // region Dagger

    fun getDroidpetAppComponent(): DroidpetAppComponent = droidpetAppComponent

    private lateinit var droidpetAppComponent: DroidpetAppComponent
    private var daggerComponents: MutableMap<String, DroidpetComponent> = mutableMapOf()
    private lateinit var networkComponent: NetworkComponent

    fun initDagger() {
        networkComponent = DaggerNetworkComponent.create()
        droidpetAppComponent = DaggerDroidpetAppComponent.create()
    }

    fun getDaggerComponent(componentName: String): DroidpetComponent {
        if (!daggerComponents.containsKey(componentName)) {
            daggerComponents[componentName] = when (componentName) {
                GithubApiComponent::class.simpleName -> DaggerGithubApiComponent
                    .builder()
                    .networkComponent(networkComponent)
                    .build()
                else -> throw Throwable("illegal component: <$componentName>")
            }
        }
        return daggerComponents.getValue(componentName)
    }

    // TODO: remove from Dagger

    // endregion
}
