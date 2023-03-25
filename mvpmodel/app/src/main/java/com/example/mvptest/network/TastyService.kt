package com.example.mvptest.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TastyApiService {
    @POST("function-ward-office")
    fun getLocationDistanceTo(
        @Body data: RequestLocationData
    ): Call<FinalStoreDataModel>
}