package com.example.task.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.Model.ImageResponse
import com.example.task.Repository.ImageRepository


class ImageViewModel(private var repository: ImageRepository) : ViewModel(){
    var imageList : MutableLiveData<ArrayList<ImageResponse>> = repository.getImageList()

    fun fetchData(page:Int,pageSize:Int){
        repository.fetchData(page,pageSize)
    }
}