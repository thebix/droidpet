package net.thebix.droidpet.navigation

import android.content.Context
import net.thebix.droidpet.launch.developer.navigation.DeveloperFragmentNavigator
import net.thebix.droidpet.di.ApplicationContext
import net.thebix.droidpet.github.GithubActivity
import javax.inject.Inject

class NavigationManager @Inject constructor(
        @ApplicationContext private val appContext: Context
) : DeveloperFragmentNavigator {
    // TODO: fragmentManager should be injected
//    override fun goToRepolist(fragmentManager: FragmentManager) {
//        fragmentManager.beginTransaction()
//            .apply {
//                replace(R.id.activity_droidpet_root, RepolistFragment.newInstance(), RepolistFragment::class.simpleName)
//                addToBackStack(null)
//                commit()
//            }
//    }
    override fun goToGithub() {
        GithubActivity.launch(appContext)
    }
}
