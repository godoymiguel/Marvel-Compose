package com.godamy.marvelcompose.data.network

import com.godamy.marvelcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, BuildConfig.MARVEL_PRIVATE_KEY, BuildConfig.MARVEL_PUBLIC_KEY)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
