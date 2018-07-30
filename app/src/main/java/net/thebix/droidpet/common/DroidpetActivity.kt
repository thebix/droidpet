package net.thebix.droidpet.common

import android.support.v7.app.AppCompatActivity
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
