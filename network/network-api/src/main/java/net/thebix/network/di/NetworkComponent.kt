@file:Suppress("PackageDirectoryMismatch")

package net.thebix.network.di

import retrofit2.Retrofit
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope

interface NetworkComponent {

    fun getRetrofitBuilder(): Retrofit.Builder

    /**
     * Info: in case of set custom timeout, use custom builder
     *
     * interface Builder {
     *      fun timeout(timeout: int): Builder
     *      fun build(): NetworkComponent
     * }
     */

}
