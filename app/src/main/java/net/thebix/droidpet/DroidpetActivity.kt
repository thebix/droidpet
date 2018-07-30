package net.thebix.droidpet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.thebix.droidpet.common.DroidpetComponent
import net.thebix.droidpet.common.DroidpetComponentBuilder
import net.thebix.droidpet.common.HasDroidpetSubcomponentBuilders
import net.thebix.droidpet.developer.DeveloperFragment
import net.thebix.droidpet.di.DaggerDroidpetActivityComponent
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class DroidpetActivity : AppCompatActivity(),
                         HasDroidpetSubcomponentBuilders {

    @Inject
    lateinit var droidpetComponentBuilders: Map<Class<out DroidpetComponent>, @JvmSuppressWildcards Provider<DroidpetComponentBuilder<*>>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Application started")

        val droidpetActivityComponent = DaggerDroidpetActivityComponent.builder()
            .appComponent((application as DroidpetApp).droidpetAppComponent)
            .build()
        droidpetActivityComponent.inject(this)

        setContentView(R.layout.activity_droidpet)

        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.activity_droidpet_root, DeveloperFragment.newInstance(), DeveloperFragment::class.simpleName)
                commit()
            }
    }

    override fun getDroidpetComponentBuilder(droidpetComponentClass: Class<out DroidpetComponent>): DroidpetComponentBuilder<*> {
        return droidpetComponentBuilders[droidpetComponentClass]!!.get()
    }

}
