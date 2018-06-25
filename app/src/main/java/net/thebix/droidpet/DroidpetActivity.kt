package net.thebix.droidpet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.developer.DeveloperFragment
import timber.log.Timber

class DroidpetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Application started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_droidpet)

        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_droidpet_root, DeveloperFragment.newInstance(), DeveloperFragment::class.simpleName)
                commit()
            }
    }

}
