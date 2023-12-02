package com.example.aeontask

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://easypay.world/api-test/"

    private val apiInterceptor = ApiInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .build()

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

