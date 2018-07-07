package net.thebix.droidpet.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class OkHttpClientModule {

    companion object {
        const val CACHE_SIZE = 1 * 1024 * 1024L // 1 Mb
    }

    @Provides
    @NetworkScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor/*, cache: Cache*/): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
//            .cache(cache)
            .build()

    @Provides
    @NetworkScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { Timber.d(it) }
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }


    // TODO: provide cacheDir and remove context dependency
//    @Provides
//    @NetworkScope
//    fun provideCache(cacheFile: File): Cache =
//        Cache(cacheFile, CACHE_SIZE)
//
//    @Provides
//    @NetworkScope
//    fun provideCacheFile(@ApplicationContext context: Context): File =
//        File(context.cacheDir, "okHttpCache")
//            .apply {
//                mkdirs()
//            }
}
