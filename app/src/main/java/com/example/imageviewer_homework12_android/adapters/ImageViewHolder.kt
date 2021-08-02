package com.example.imageviewer_homework12_android.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imageviewer_homework12_android.R

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.imgItem)
    val description: TextView = view.findViewById(R.id.description)

}
