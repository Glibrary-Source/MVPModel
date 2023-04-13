package com.example.chatgpttest

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class GPTApi(private val apiKey: String) {
    private val client = OkHttpClient()

    fun generateText(prompt: String, completion: (String?, Exception?) -> Unit) {
        val url = "https://api.openai.com/v1/engines/davinci-codex/completions"

        val json = JSONObject()
        json.put("prompt", prompt)
        json.put("max_tokens", 100)

        val body = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            json.toString()
        )

        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: okio.IOException) {
                completion(null, e)
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (!response.isSuccessful) {
                    completion(null, Exception("Unexpected response: $response"))
                    return
                }

                val jsonString = response.body.string()
                val jsonObject = JSONObject(jsonString)

                val completions = jsonObject.getJSONArray("choices")
                val text = completions.getJSONObject(0).getString("text")

                completion(text, null)
            }
        })
    }
}