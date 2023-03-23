package com.example.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvptest.databinding.ActivityMainBinding
import com.example.mvptest.model.InfoRepository
import com.example.mvptest.presenter.Presenter


class MainActivity : AppCompatActivity() {

    private lateinit var presenter: Presenter
    private lateinit var repository: InfoRepository
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        repository = InfoRepository(this)
        presenter = Presenter(this@MainActivity, repository)

    }


}
