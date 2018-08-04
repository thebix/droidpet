package net.thebix.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(
    includes = [
        OkHttpClientModule::class
    ]
)
class ApiModule {

    @Provides
    @NetworkScope
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @NetworkScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @NetworkScope
    fun provideRetrofitBuilder(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory,
            provideRxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(provideRxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
}
