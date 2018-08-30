package net.thebix.launch

import android.os.Bundle
import net.thebix.common_android.DroidpetActivity
import net.thebix.common_android.DroidpetAppBase
import net.thebix.launch.developer.DeveloperFragment
import net.thebix.launch.di.DaggerLaunchActivityComponent
import timber.log.Timber

class LaunchActivity : DroidpetActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Launch activity started")
        val launchActivityComponent = DaggerLaunchActivityComponent.builder()
            .commonAndroidComponent((application as DroidpetAppBase).getCommonAndroidComponent())
            .build()
        launchActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launch)

        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_launch_root, DeveloperFragment.newInstance(), DeveloperFragment::class.simpleName)
                commit()
            }
    }

}
