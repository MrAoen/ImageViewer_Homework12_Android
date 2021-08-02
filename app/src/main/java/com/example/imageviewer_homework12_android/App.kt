package com.example.imageviewer_homework12_android

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.imageviewer_homework12_android.model.ImageItem

class App:Application() {

    companion object{
        var imgList = mutableListOf<ImageItem>()
    }

    override fun onCreate() {
        super.onCreate()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.all.forEach { (key, vals) ->
            run {
                imgList.add(ImageItem (key, vals as String))
            }
        }
    }

}
