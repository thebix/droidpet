package net.thebix.droidpet.navigation

import android.content.Context
import net.thebix.common_android.NavigationManager
import net.thebix.common_android.NavigationParams
import net.thebix.droidpet.splash.SplashActivity
import net.thebix.github.GithubActivity
import net.thebix.info.InfoActivity
import net.thebix.launch.LaunchActivity

internal class NavigationManagerImpl(context: Context) : NavigationManager {

    private val appContext = context.applicationContext

    override fun startScreen(screenName: String, params: NavigationParams?) {
        when (screenName) {
            "Github" -> appContext.startActivity(GithubActivity.createIntent(appContext))
            "Error" -> {
                if (params is NavigationParams.ErrorParams) {
                    appContext.startActivity(InfoActivity.createIntent(appContext, params))
                } else {
                    appContext.startActivity(InfoActivity.createIntent(appContext))
                }
            }
            "Launch" -> appContext.startActivity(LaunchActivity.createIntent(appContext))
            else -> appContext.startActivity(SplashActivity.createIntent(appContext))
        }

    }
}
