package net.thebix.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// TODO: add timber as project / to common library / something else and remove slf4j

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
    fun provideLoggingInterceptor(logger: Logger): HttpLoggingInterceptor =
        HttpLoggingInterceptor {
            logger.debug(it)
        }
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    @Provides
    @NetworkScope
    fun provideLogger(): Logger = LoggerFactory.getLogger(OkHttpClientModule::class.java)

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
