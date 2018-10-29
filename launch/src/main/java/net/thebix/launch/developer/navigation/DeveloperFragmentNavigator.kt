package net.thebix.launch.developer.navigation

import net.thebix.common.android.NavigationManager
import javax.inject.Inject

interface DeveloperFragmentNavigator {

    fun goToGithub()
}

class DeveloperFragmentNavigatorImpl @Inject constructor(
        private val navigationManager: NavigationManager
) : DeveloperFragmentNavigator {

    override fun goToGithub() {
        navigationManager.startScreen("Github")
    }
}
