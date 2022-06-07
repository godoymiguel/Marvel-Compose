package com.godamy.marvelcompose.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_ENDPOINT = "https://gateway.marvel.com/"

object ApiClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(QueryInterceptor())
        .build()

    private val restAdapater = Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val comicsService: ComicsService = restAdapater.create(ComicsService::class.java)
}
