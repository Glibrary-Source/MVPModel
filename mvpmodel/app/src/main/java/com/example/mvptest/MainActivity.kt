package com.example.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvptest.contractor.TastyContract
import com.example.mvptest.databinding.ActivityMainBinding
import com.example.mvptest.model.FinalStoreDataModel
import com.example.mvptest.presenter.TastyPresenter

class MainActivity : AppCompatActivity(), TastyContract.View{

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: TastyContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = TastyPresenter(this)
        presenter.getTasty()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container_fragment,MvpFragment())
        transaction.commit()

        binding.btnChangeFragment.setOnClickListener {
            transaction.replace(R.id.container_fragment, MvpFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun showTasty(data: FinalStoreDataModel) {
        binding.outputEmail.text = data.toString()
    }

    override fun showError(error: String) {
        binding.outputEmail.text = error
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
