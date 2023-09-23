package com.example.task.Api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtilites {
    private const val BASE_URL = "https://api.unsplash.com"

    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)

    }
}

