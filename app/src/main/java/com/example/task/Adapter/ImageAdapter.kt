package com.example.task.Adapter


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.Model.ImageResponse
import com.example.task.databinding.BoxLayoutBinding


class ImageAdapter(private var itemList: ArrayList<ImageResponse>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val binding = BoxLayoutBinding.inflate(LayoutInflater.from(parent.context
        ),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner  class ViewHolder(private var binding: BoxLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ImageResponse,position: Int){
            binding.title.text = item.user?.bio


            binding.edtText.setText(itemList[position].edtDescription)
            binding.edtText.addTextChangedListener(object :TextWatcher{


                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                                    }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    itemList[position].edtDescription = s.toString()

                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            Glide.with(binding.root.context)
                .load(item.urls?.regular)
                .into(binding.profileImage)

        }
    }

    fun updateAdapter(itemList:ArrayList<ImageResponse>){
        itemList.forEach {
            this.itemList.add(it)
        }
        notifyDataSetChanged()
    }
    fun getEditTextValues() : ArrayList<String>{
        val editTextValues = ArrayList<String>()
        for (item in itemList){
            editTextValues.add(item.edtDescription.toString())

        }
        return editTextValues
    }


}