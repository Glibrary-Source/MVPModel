package com.example.mvptest.presenter

import com.example.mvptest.contractor.TastyContract
import com.example.mvptest.network.*
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TastyPresenter(private val view: TastyContract.View) : TastyContract.Presenter {

    private val apiService = TastyApi.service
    private var call: Call<FinalStoreDataModel>? = null

    override fun getTasty() {
        val position = LatLng(37.510402, 126.945915)
        val myLocation = listOf(position.latitude, position.longitude)
        val requestType = RequestLocationData("How_long", myLocation)
        call = apiService.getLocationDistanceTo(requestType)
        call?.enqueue(object  : Callback<FinalStoreDataModel> {
            override fun onResponse(
                call: Call<FinalStoreDataModel>,
                response: Response<FinalStoreDataModel>
            ) {
                if(response.isSuccessful) {
                    view.showTasty(response.body()!!)
                } else {
                    view.showError("Error:${response.code()}")
                }
            }

            override fun onFailure(call: Call<FinalStoreDataModel>, t: Throwable) {
                view.showError("Error:${t.message}")
            }
        })
    }

    override fun onDestroy() {
        call?.cancel()
    }

}