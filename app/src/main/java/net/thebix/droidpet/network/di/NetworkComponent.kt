package net.thebix.droidpet.network.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope

@NetworkScope
@Component(
    modules = [
        ApiModule::class
    ]
)
interface NetworkComponent {
    fun getRetrofitBuilder(): Retrofit.Builder
}
