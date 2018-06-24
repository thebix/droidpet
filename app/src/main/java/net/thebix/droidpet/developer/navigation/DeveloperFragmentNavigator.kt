package net.thebix.droidpet.developer.navigation

import android.support.v4.app.FragmentManager

interface DeveloperFragmentNavigator {
    // TODO: fragmentManager should be injected
    fun goToRepolist(fragmentManager: FragmentManager)
}
