package net.thebix.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import net.thebix.common.android.DroidpetActivity
import net.thebix.common.android.DroidpetAppBase
import net.thebix.github.di.DaggerGithubActivityComponent
import net.thebix.github.repolist.mvi.RepolistFragment
import net.thebix.network.di.NetworkComponent
import net.thebix.network.di.create

class GithubActivity : DroidpetActivity() {

    companion object {

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, GithubActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val githubActivityComponent = DaggerGithubActivityComponent.builder()
            .commonComponent((application as DroidpetAppBase).getCommonComponent())
            .networkComponent(NetworkComponent.create())
            .build()
        githubActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_github)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .apply {
                    replace(R.id.activity_github_root, RepolistFragment.newInstance(), RepolistFragment::class.simpleName)
                    commit()
                }
        }
    }
}
