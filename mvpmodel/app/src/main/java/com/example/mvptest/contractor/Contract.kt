package com.example.mvptest.contractor

import org.json.JSONObject

interface Contract {

    interface View {
        fun showInfo(info: JSONObject)
    }

    interface Presenter {
        fun initInfo()
        fun setInfo(info: JSONObject)
        fun saveInfo(info: JSONObject)
    }

}