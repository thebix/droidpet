package net.thebix.droidpet.launch

import android.os.Bundle
import net.thebix.droidpet.DroidpetApp
import net.thebix.droidpet.R
import net.thebix.droidpet.common.DroidpetActivity
import net.thebix.droidpet.launch.developer.DeveloperFragment
import net.thebix.droidpet.launch.di.DaggerLaunchActivityComponent
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
