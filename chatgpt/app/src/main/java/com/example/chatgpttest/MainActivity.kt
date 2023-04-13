package com.example.chatgpttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val apiKey = "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"
        val gptApi = GPTApi(apiKey)

        val prompt = "Once upon a time,"
        gptApi.generateText(prompt) { response, error ->
            if (error != null) {
                Log.d("testGPT", error.toString())
            } else if (response != null) {
                Log.d("testGPT", response.toString())
            }
        }


    }
}