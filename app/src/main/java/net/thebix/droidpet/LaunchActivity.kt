package net.thebix.droidpet

import android.os.Bundle
import net.thebix.droidpet.common.DroidpetActivity
import net.thebix.droidpet.developer.DeveloperFragment
import net.thebix.droidpet.di.DaggerLaunchActivityComponent
import timber.log.Timber

class LaunchActivity : DroidpetActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Application started")

        val launchActivityComponent = DaggerLaunchActivityComponent.builder()
            .appComponent((application as DroidpetApp).droidpetAppComponent)
            .build()
        launchActivityComponent.inject(this)

        setContentView(R.layout.activity_launch)

        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_launch_root, DeveloperFragment.newInstance(), DeveloperFragment::class.simpleName)
                commit()
            }
    }

}
