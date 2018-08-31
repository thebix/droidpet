package net.thebix.droidpet.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.DroidpetApp
import net.thebix.droidpet.R
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Application started")

        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        (application as DroidpetApp).getCommonAndroidComponent().getNavigationManager().startScreen("Launch")
    }

}
