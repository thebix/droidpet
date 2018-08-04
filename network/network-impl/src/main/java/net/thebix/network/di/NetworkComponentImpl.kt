package net.thebix.network.di

import dagger.Component
import retrofit2.Retrofit

@NetworkScope
@Component(
    modules = [
        ApiModule::class
    ]
)
interface NetworkComponentImpl : NetworkComponent {

    override fun getRetrofitBuilder(): Retrofit.Builder

}
