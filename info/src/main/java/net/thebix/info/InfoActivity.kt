package net.thebix.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.common_android.NavigationManager.Companion.EXTRA_INFO
import net.thebix.common_android.NavigationManager.Companion.EXTRA_SUBTITLE
import net.thebix.common_android.NavigationManager.Companion.EXTRA_TITLE
import net.thebix.common_android.NavigationParams
import net.thebix.info.error.ErrorFragment

class InfoActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun createIntent(context: Context, errorParams: NavigationParams.ErrorParams? = null): Intent {
            val intent = Intent(context, InfoActivity::class.java)
            if (errorParams != null) {
                intent.apply {
                    putExtra(EXTRA_TITLE, errorParams.title)
                    putExtra(EXTRA_SUBTITLE, errorParams.subtitle)
                    putExtra(EXTRA_INFO, errorParams.info)
                }
            }
            return intent
        }

        // INFO: other createIntents should have params as required
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_info)

        if (savedInstanceState == null) {
            val title = intent.getStringExtra(EXTRA_TITLE)
            val subtitle = intent.getStringExtra(EXTRA_SUBTITLE)
            val info = intent.getStringExtra(EXTRA_INFO)
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.activity_info_root, ErrorFragment.newInstance(title, subtitle, info))
                    commit()
                }
        }
    }

}
