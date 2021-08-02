package com.example.imageviewer_homework12_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageviewer_homework12_android.ImageListFragment
import com.example.imageviewer_homework12_android.R
import com.example.imageviewer_homework12_android.model.ImageItem

class ImageReyclerViewAdapter(val list: List<ImageItem>, context: ImageListFragment) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val clickHandler: ClickHandler = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        list[position].apply {
            holder.description.text = description
            holder.image.adjustViewBounds = true
            Glide.with(holder.itemView).load(imageUrl).into(holder.image)
            holder.itemView.setOnClickListener {
                clickHandler.itemClick(this)
            }
        }
    }

    override fun getItemCount() = list.size

}