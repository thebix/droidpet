package net.thebix.droidpet.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.R
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.HasDroidpetSubcomponentBuilders
import net.thebix.droidpet.github.di.DaggerGithubActivityComponent
import net.thebix.droidpet.github.repolist.RepolistFragment
import net.thebix.droidpet.network.di.DaggerNetworkComponent
import javax.inject.Inject
import javax.inject.Provider

class GithubActivity : AppCompatActivity(),
                       HasDroidpetSubcomponentBuilders {

    companion object {

        @Suppress("MemberVisibilityCanBePrivate")
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, GithubActivity::class.java)

        @JvmStatic
        fun launch(context: Context) {
            context.startActivity(createIntent(context))
        }
    }

    @Inject
    lateinit var droidpetComponentBuilders: Map<Class<out DroidpetComponent>, @JvmSuppressWildcards Provider<DroidpetComponentBuilder<*>>>

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

    override fun getDroidpetComponentBuilder(droidpetComponentClass: Class<out DroidpetComponent>): DroidpetComponentBuilder<*> {
        return droidpetComponentBuilders[droidpetComponentClass]!!.get()
    }

}
