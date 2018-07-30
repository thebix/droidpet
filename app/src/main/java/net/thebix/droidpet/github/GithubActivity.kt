package net.thebix.droidpet.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import net.thebix.droidpet.R
import net.thebix.droidpet.common.DroidpetActivity
import net.thebix.droidpet.github.di.DaggerGithubActivityComponent
import net.thebix.droidpet.github.repolist.RepolistFragment
import net.thebix.droidpet.network.di.DaggerNetworkComponent

class GithubActivity : DroidpetActivity() {

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

        val githubActivityComponent = DaggerGithubActivityComponent.builder()
            .networkComponent(DaggerNetworkComponent.create())
            .build()
        githubActivityComponent.inject(this)

        setContentView(R.layout.activity_github)
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_github_root, RepolistFragment.newInstance(), RepolistFragment::class.simpleName)
                commit()
            }
    }

}
