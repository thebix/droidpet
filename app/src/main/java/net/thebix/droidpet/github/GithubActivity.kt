package net.thebix.droidpet.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.R
import net.thebix.droidpet.github.repolist.RepolistFragment

class GithubActivity : AppCompatActivity() {

    companion object {

        @Suppress("MemberVisibilityCanBePrivate")
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, GithubActivity::class.java)

        @JvmStatic
        fun launch(context: Context) {
            context.startActivity(createIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_github_root, RepolistFragment.newInstance(), RepolistFragment::class.simpleName)
                commit()
            }
    }

}
