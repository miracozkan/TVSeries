package com.miracozkan.tvseries.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.miracozkan.tvseries.BuildConfig
import com.miracozkan.tvseries.datalayer.network.ProjectService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:17          │
//└─────────────────────────────┘

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): ProjectService {
        return retrofit.baseUrl(BuildConfig.API_URL)
            .build()
            .create(ProjectService::class.java)
    }
}