package com.example.mvptest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvptest.contractor.TastyContract
import com.example.mvptest.databinding.FragmentMvpBinding
import com.example.mvptest.model.FinalStoreDataModel
import com.example.mvptest.presenter.TastyPresenter

class MvpFragment : Fragment(), TastyContract.View {

    private lateinit var binding: FragmentMvpBinding
    private lateinit var presenter: TastyContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMvpBinding.inflate(inflater)

        presenter = TastyPresenter(this)
        presenter.getTasty()

        return binding.root
    }

    override fun showTasty(data: FinalStoreDataModel) {
        binding.textTest.text = data.toString()
    }

    override fun showError(error: String) {
        binding.textTest.text = error
    }

}