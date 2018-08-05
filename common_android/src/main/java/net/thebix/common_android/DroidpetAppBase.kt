@file:Suppress("PackageName")

package net.thebix.common_android

import android.app.Application
import net.thebix.common.di.CommonComponent

abstract class DroidpetAppBase : Application() {

    abstract fun getCommonComponent(): CommonComponent

    abstract fun getCommonAndroidComponent(): CommonAndroidComponent

}
