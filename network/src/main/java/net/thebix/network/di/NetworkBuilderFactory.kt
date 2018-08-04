package net.thebix.network.di

/**
 *  Info: in case of set custom timeout, use custom builder
 *  fun NetworkComponent.Companion.builder(): NetworkComponent.Builder {
 *      return DaggerNetworkComponentImpl.builder()
 *  }
 */


fun NetworkComponent.Companion.create(): NetworkComponent {
    return DaggerNetworkComponentImpl.create()
}
