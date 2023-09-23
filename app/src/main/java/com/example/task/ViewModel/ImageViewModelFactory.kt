package com.example.task.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.Repository.ImageRepository

import java.lang.IllegalArgumentException

class ImageViewModelFactory(private var repository: ImageRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)){
            return ImageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}