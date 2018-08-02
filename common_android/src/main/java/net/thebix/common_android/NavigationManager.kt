@file:Suppress("PackageName")

package net.thebix.common_android

import android.os.Bundle

interface NavigationManager {

    fun startScreen(screenName: String, bundle: Bundle? = null)

}
