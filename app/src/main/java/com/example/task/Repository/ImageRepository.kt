package com.example.task.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.task.Api.ApiInterface
import com.example.task.Model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository(private val apiInterface: ApiInterface) {
    private val imageList = MutableLiveData<ArrayList<ImageResponse>>()

    fun getImageList(): MutableLiveData<ArrayList<ImageResponse>> {
        return imageList
    }

    fun fetchData(page: Int, pageSize: Int) {
        val call = apiInterface.getImages(page, pageSize)
        call.enqueue(object : Callback<List<ImageResponse>> {
            override fun onResponse(call: Call<List<ImageResponse>>, response: Response<List<ImageResponse>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    imageList.postValue(images as ArrayList<ImageResponse>)
                }
            }

            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
               Log.e("ERROR",t.message.toString())
            }
        })
    }
}
