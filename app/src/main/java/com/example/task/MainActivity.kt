package com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.Adapter.ImageAdapter

import com.example.task.ViewModel.ImageViewModel
import com.example.task.ViewModel.ImageViewModelFactory
import com.example.task.databinding.ActivityMainBinding

import com.example.task.Api.ApiUtilites
import com.example.task.Repository.ImageRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageViewModel
    private lateinit var adapter: ImageAdapter
    private lateinit var rcView: RecyclerView
    private var page = 1
    private val pageSize = 15
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rcView = binding.rcView
        adapter = ImageAdapter(arrayListOf())
        val manager = LinearLayoutManager(this)
        rcView.layoutManager = manager
        rcView.adapter = adapter


        val apiClient = ApiUtilites.create()

        val repository = ImageRepository(apiClient)
        val factory = ImageViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ImageViewModel::class.java)
        viewModel.imageList.observe(this) { imageList ->
            isLoading = false
            adapter.updateAdapter(imageList)
        }
        fetchData()
        binding.btnSubmit.setOnClickListener {
            val editTextValues = adapter.getEditTextValues()
            for ((index,value )in editTextValues.withIndex()){
                Log.d("EditText","EditText Values for item $index : $value")
            }
        }

        rcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= pageSize
                    ) {
                        page++
                        fetchData()
                    }
                }
            }
        })
    }

    private fun fetchData() {
        isLoading = true
        viewModel.fetchData(page, pageSize)
    }
}
