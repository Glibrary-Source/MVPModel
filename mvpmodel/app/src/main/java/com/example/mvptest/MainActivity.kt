package com.example.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvptest.contractor.Contract
import com.example.mvptest.databinding.ActivityMainBinding
import com.example.mvptest.model.InfoRepository
import com.example.mvptest.presenter.Presenter
import org.json.JSONObject


class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Presenter
    private lateinit var repository: InfoRepository
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        repository = InfoRepository(this)
        presenter = Presenter(this@MainActivity, repository)

        presenter.initInfo()
        initButtonListener()

    }

    override fun showInfo(info: JSONObject) {
        binding.outputName.text = info.getString("name")
        binding.outputEmail.text = info.getString("email")

        presenter.initInfo()
        initButtonListener()
    }

    private fun initButtonListener() {
        binding.btnSave.setOnClickListener {
            Log.d("KBW", "hi")


            var info = JSONObject()
            info.put("name", binding.editName.text.toString())
            info.put("email", binding.editEmail.text.toString())

            binding.editName.text.clear()
            binding.editEmail.text.clear()

            presenter.setInfo(info)
            presenter.saveInfo(info)
        }
    }
}
