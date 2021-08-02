package com.example.imageviewer_homework12_android.adapters

import com.example.imageviewer_homework12_android.model.ImageItem

interface ClickHandler {
    fun itemClick(holder :ImageItem)
}