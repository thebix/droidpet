package net.thebix.launch.developer.navigation

import android.content.Context
import net.thebix.common.ApplicationContext
import net.thebix.common_android.NavigationManager
import net.thebix.launch.developer.DeveloperFragment
import javax.inject.Inject

interface DeveloperFragmentNavigator {

    companion object {
        val scope = DeveloperFragment::class.java.canonicalName!!
    }

    fun goToGithub()
}

class DeveloperFragmentNavigatorImpl @Inject constructor(
        @ApplicationContext private val appContext: Context,
//        private val navigatorHolder: NavigatorHolder
        private val navigationManager: NavigationManager
) : DeveloperFragmentNavigator {

//    private val navigator get () = navigatorHolder.getNavigator(scope)

    override fun goToGithub() {
//        navigator.openActivity(GithubActivity.createIntent(appContext))
        navigationManager.startScreen("Github")
    }
}
