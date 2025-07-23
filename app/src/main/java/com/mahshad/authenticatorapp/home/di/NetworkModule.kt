package com.mahshad.authenticatorapp.home.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient? = null

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory? = null

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit? = null
}