package net.thebix.network.di

import dagger.Component

@NetworkScope
@Component(
    modules = [
        ApiModule::class
    ]
)
interface NetworkComponentImpl : NetworkComponent {

    /**
     * Info: in case of set custom timeout, use custom builder
     *  @Component.Builder
     *  interface Builder: NetworkComponent.Builder {
     *      @BindsInstance
     *      override fun timeout(timeout: Int): Builder
     *  }
     */

}
