@file:Suppress("PackageName")

package net.thebix.common.android

interface NavigationManager {

    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_SUBTITLE = "EXTRA_SUBTITLE"
        const val EXTRA_INFO = "EXTRA_INFO"
    }

    fun startScreen(screenName: String, params: NavigationParams? = null)

}

sealed class NavigationParams {

    class ErrorParams(
            val title: String = "",
            val subtitle: String = "",
            val info: String = ""
    ) : NavigationParams()

}
