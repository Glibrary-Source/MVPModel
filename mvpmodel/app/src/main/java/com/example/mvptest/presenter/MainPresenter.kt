package com.example.mvptest.presenter

import com.example.mvptest.contractor.MainContract
import com.example.mvptest.model.User
import com.example.mvptest.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val apiService = ApiClient.apiService
    private var call: Call<List<User>>? = null

    override fun getUsers() {
        call = apiService.getUsers()
        call?.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) {
                    view.showUsers(response.body()!!)
                }else{
                    view.showError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                view.showError("Error:${t.message}")
            }
        })

    }

    override fun onDestroy() {
        call?.cancel()
    }
}