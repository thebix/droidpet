package net.thebix.droidpet.navigation

import android.content.Context
import android.os.Bundle
import net.thebix.common_android.NavigationManager
import net.thebix.droidpet.splash.SplashActivity
import net.thebix.github.GithubActivity

internal class NavigationManagerImpl(context: Context) : NavigationManager {

    private val appContext = context.applicationContext

    override fun startScreen(screenName: String, bundle: Bundle?) {
        when (screenName) {
            "Github" -> appContext.startActivity(GithubActivity.createIntent(appContext))
        // TODO: common error screen (with passed text)
            else -> appContext.startActivity(SplashActivity.createIntent(appContext))
        }

    }
}
