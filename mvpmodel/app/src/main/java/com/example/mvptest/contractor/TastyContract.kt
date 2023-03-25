package com.example.mvptest.contractor

import com.example.mvptest.network.FinalStoreDataModel

interface TastyContract {

    interface View {
        fun showTasty(data: FinalStoreDataModel)
        fun showError(error: String)
    }

    interface Presenter {
        fun getTasty()
        fun onDestroy()
    }
}