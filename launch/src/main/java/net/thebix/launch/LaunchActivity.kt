package net.thebix.launch

import android.os.Bundle
import net.thebix.common_android.DroidpetActivity
import net.thebix.common_android.DroidpetAppBase
import net.thebix.launch.developer.DeveloperFragment
import net.thebix.launch.di.DaggerLaunchActivityComponent
import timber.log.Timber

class LaunchActivity : DroidpetActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Launch activity started")

        val launchActivityComponent = DaggerLaunchActivityComponent.builder()
            .commonComponent((application as DroidpetAppBase).getCommonComponent())
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
