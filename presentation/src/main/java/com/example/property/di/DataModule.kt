package com.example.property.di

import com.example.data.api.ItemAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideItemAPI(retrofit: Retrofit): ItemAPI =
        retrofit.create(ItemAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, logging: HttpLoggingInterceptor): Retrofit = Retrofit.Builder()
        .baseUrl("https://pastebin.com/raw/")
        .client(OkHttpClient.Builder().addInterceptor(logging).build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()

}
