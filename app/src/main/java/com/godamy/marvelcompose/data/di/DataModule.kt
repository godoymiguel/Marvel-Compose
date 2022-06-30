package com.godamy.marvelcompose.data.di

import com.godamy.marvelcompose.data.network.remote.CharactersService
import com.godamy.marvelcompose.data.network.remote.ComicsService
import com.godamy.marvelcompose.data.network.remote.EventsService
import com.godamy.marvelcompose.data.network.remote.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndPoint
    fun provideApiEndPoint(): String = "https://gateway.marvel.com/"

    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(queryInterceptor)
            .build()

    @Provides
    fun provideRestAdapter(@ApiEndPoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCharactersService(restAdapter: Retrofit): CharactersService = restAdapter.create()

    @Provides
    fun provideComicsService(restAdapter: Retrofit): ComicsService = restAdapter.create()

    @Provides
    fun provideEventsService(restAdapter: Retrofit): EventsService = restAdapter.create()
}