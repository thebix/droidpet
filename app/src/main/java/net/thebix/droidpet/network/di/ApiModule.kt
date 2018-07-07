package net.thebix.droidpet.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
}
