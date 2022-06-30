package com.godamy.marvelcompose.di

import com.godamy.marvelcompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @PrivateKey
    fun providePrivateKey(): String = BuildConfig.MARVEL_PRIVATE_KEY

    @Provides
    @PublicKey
    fun providePublicKey(): String = BuildConfig.MARVEL_PUBLIC_KEY
}