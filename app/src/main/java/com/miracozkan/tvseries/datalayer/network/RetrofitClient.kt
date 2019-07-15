package com.miracozkan.tvseries.datalayer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.miracozkan.tvseries.BuildConfig.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 28/06/19 - 18:19            │
//└─────────────────────────────┘

class RetrofitClient {
    companion object {
        fun getClient(): ProjectService {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ProjectService::class.java)
        }
    }
}