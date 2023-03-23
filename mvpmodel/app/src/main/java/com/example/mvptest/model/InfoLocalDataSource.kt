package com.example.mvptest.model

import android.content.Context
import org.json.JSONObject

interface InfoDataSource {

    interface LoadInfoCallback {
        fun onInfoLoaded(info: JSONObject)
        fun onDataNotAvailable()
    }

    fun getInfo(callback: LoadInfoCallback)
    fun saveInfo(info: JSONObject)
}

class InfoLocalDataSource(context: Context) : InfoDataSource {

    private val sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        var info = sharedPreferences.getString("info", null)
        if(info != null) {
            callback.onInfoLoaded(JSONObject(info))
        } else {
            callback.onDataNotAvailable()
        }
    }

    override fun saveInfo(info: JSONObject) {
        editor.putString("info", info.toString())
        editor.commit()
    }

}

class InfoRepository(context: Context) : InfoDataSource {
    private val infoLocalDataSource = InfoLocalDataSource(context)

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        infoLocalDataSource.getInfo(callback)
    }

    override fun saveInfo(info: JSONObject) {
        infoLocalDataSource.saveInfo(info)
    }

}