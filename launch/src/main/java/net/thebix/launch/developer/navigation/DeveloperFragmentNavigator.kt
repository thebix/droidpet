package net.thebix.launch.developer.navigation

import android.content.Context
import net.thebix.common.ApplicationContext
import net.thebix.common_android.NavigatorHolder
import net.thebix.launch.developer.DeveloperFragment
import net.thebix.launch.developer.navigation.DeveloperFragmentNavigator.Companion.scope
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
//        navigator.openActivity(GithubActivity.createIntent(appContext))
    }
}
