package com.example.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvptest.contractor.MainContract
import com.example.mvptest.databinding.ActivityMainBinding
import com.example.mvptest.model.User
import com.example.mvptest.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View{

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        presenter = MainPresenter(this)
        presenter.getUsers()



    }

    override fun showUsers(users: List<User>) {
        binding.outputEmail.text = users.toString()
    }

    override fun showError(error: String) {
        binding.outputEmail.text = error
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}
