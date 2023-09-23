package com.example.task.Api


import com.example.task.Model.ImageResponse
import com.example.task.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {


    @Headers("Authorization: Client-ID " + API_KEY)
    @GET("/photos")

    fun  getImages(
        @Query("page") page: Int,
        @Query("per_page") per_page :Int,
    ) : Call<List<ImageResponse>>
}