package com.example.mvptest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TastyApi {

    private const val BASE_URL =
        "https://asia-northeast3-project-ward-office.cloudfunctions.net/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val service: TastyApiService by lazy {
        retrofit.create(TastyApiService::class.java)
    }
}