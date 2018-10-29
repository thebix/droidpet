@file:Suppress("PackageName")

package net.thebix.common.android

import android.support.v7.app.AppCompatActivity
import net.thebix.common.DroidpetComponent
import net.thebix.common.DroidpetComponentBuilder
import net.thebix.common.HasDroidpetSubcomponentBuilders
import javax.inject.Inject
import javax.inject.Provider

abstract class DroidpetActivity : AppCompatActivity(),
                                  HasDroidpetSubcomponentBuilders {
    @Inject
    lateinit var droidpetComponentBuilders: Map<Class<out DroidpetComponent>, @JvmSuppressWildcards Provider<DroidpetComponentBuilder<*>>>

    override fun getDroidpetComponentBuilder(droidpetComponentClass: Class<out DroidpetComponent>): DroidpetComponentBuilder<*> {
        return droidpetComponentBuilders[droidpetComponentClass]!!.get()
    }

}
