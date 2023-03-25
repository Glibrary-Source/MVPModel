package com.example.mvptest.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL =
    "https://asia-northeast3-project-ward-office.cloudfunctions.net/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TestApiService {
    @POST("function-ward-office")
    fun getLocationDistanceTo(
        @Body data: RequestLocationData
    ): Call<FinalStoreDataModel>
}

object TestApi {
    val service: TestApiService by lazy {
        retrofit.create(TestApiService::class.java)
    }
}
