package com.godamy.marvelcompose.data.network.remote

import com.godamy.marvelcompose.data.network.generateHash
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class QueryInterceptor @Inject constructor(
    private val privateKey: String,
    private val publicKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
