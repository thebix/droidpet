package net.thebix.droidpet.github.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class LiggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("Sending request url: <${request.url()}>, headers: <${request.headers()}>")
        val response = chain.proceed(request)
        Timber.d("Receiving response <$response>")
        return response
    }

}
