package dev.jinkim.android.helloword.network

import dev.jinkim.android.helloword.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // NOTE: openWeatherApiKey stored in local.properties to keep it from being checked in.
        val url = req.url.newBuilder().addQueryParameter("appid", BuildConfig.openWeatherApiKey).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
