package com.example.mvptest.contractor

import com.example.mvptest.model.User

interface MainContract {

    interface View{
        fun showUsers(users: List<User>)
        fun showError(error: String)
    }

    interface Presenter {
        fun getUsers()
        fun onDestroy()
    }
}