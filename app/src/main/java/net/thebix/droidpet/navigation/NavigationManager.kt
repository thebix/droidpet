package net.thebix.droidpet.navigation

import android.support.v4.app.FragmentManager
import net.thebix.droidpet.R
import net.thebix.droidpet.developer.navigation.DeveloperFragmentNavigator
import net.thebix.droidpet.github.repolist.RepolistFragment

class NavigationManager
    : DeveloperFragmentNavigator {
    // TODO: fragmentManager should be injected
    override fun goToRepolist(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .apply {
                replace(R.id.activity_droidpet_root, RepolistFragment.newInstance(), RepolistFragment::class.simpleName)
                addToBackStack(null)
                commit()
            }
    }
}
