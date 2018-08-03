package net.thebix.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import net.thebix.common_android.DroidpetActivity
import net.thebix.github.di.DaggerGithubActivityComponent
import net.thebix.github.repolist.RepolistFragment
import net.thebix.network.di.DaggerNetworkComponent

class GithubActivity : DroidpetActivity() {

    companion object {

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, GithubActivity::class.java)
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
