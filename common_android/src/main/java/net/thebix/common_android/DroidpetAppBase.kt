@file:Suppress("PackageName")

package net.thebix.common_android

import android.app.Application

abstract class DroidpetAppBase : Application() {

    abstract fun getCommonComponent(): CommonComponent

}
