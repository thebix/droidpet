package net.thebix.droidpet.launch.developer.navigation

import android.content.Context
import net.thebix.droidpet.di.ApplicationContext
import net.thebix.droidpet.github.GithubActivity
import net.thebix.droidpet.launch.developer.DeveloperFragment
import net.thebix.droidpet.launch.developer.navigation.DeveloperFragmentNavigator.Companion.scope
import net.thebix.droidpet.navigation.NavigatorHolder
import javax.inject.Inject

interface DeveloperFragmentNavigator {

    companion object {
        val scope = DeveloperFragment::class.java.canonicalName!!
    }

    fun goToGithub()
}

class DeveloperFragmentNavigatorImpl @Inject constructor(
        @ApplicationContext private val appContext: Context,
        private val navigatorHolder: NavigatorHolder
) : DeveloperFragmentNavigator {

    private val navigator get () = navigatorHolder.getNavigator(scope)

    override fun goToGithub() {
        navigator.openActivity(GithubActivity.createIntent(appContext))
    }
}
