package net.thebix.droidpet.github.api.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.thebix.droidpet.github.network.LoggingInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

@Module(
    includes = [
        ContextModule::class
    ]
)
class OkHttpClientModule {

    companion object {
        const val CACHE_SIZE = 1 * 1024 * 1024L // 1 Mb
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

    // TODO: change to @Binds
    @Provides
    fun provideLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor()

    @Provides
    fun provideCache(cacheFile: File): Cache =
        Cache(cacheFile, CACHE_SIZE)

    @Provides
    fun provideCahceFile(context: Context): File =
        File(context.cacheDir, "okHttpCache")
            .apply {
                mkdirs()
            }
}
