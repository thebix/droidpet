package net.thebix.droidpet.github.api.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
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
    @GithubScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

    @Provides
    @GithubScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { Timber.d(it) }
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }


    @Provides
    @GithubScope
    fun provideCache(cacheFile: File): Cache =
        Cache(cacheFile, CACHE_SIZE)

    @Provides
    @GithubScope
    fun provideCacheFile(@ApplicationContext context: Context): File =
        File(context.cacheDir, "okHttpCache")
            .apply {
                mkdirs()
            }
}
