package net.thebix.droidpet.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.R
import net.thebix.launch.LaunchActivity
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Application started")

        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        // TODO: change to router
        applicationContext.startActivity(Intent(applicationContext, LaunchActivity::class.java))
    }

}
