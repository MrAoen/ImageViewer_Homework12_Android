package com.example.imageviewer_homework12_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.nio.file.Files
import java.nio.file.Paths

private const val IMG_PATH = "img_path"
private const val IMG_DESCRIPTION = "img_description"

class ImageFragment : Fragment() {
    private var imgPath: String? = null
    private var imgDesc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imgPath = it.getString(IMG_PATH)
            imgDesc = it.getString(IMG_DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_image, container, false)
        val img = fragmentView.findViewById<ImageView>(R.id.img_detail)
        val desc = fragmentView.findViewById<TextView>(R.id.img_description)
        if (Files.exists(Paths.get(imgPath))) {
            Glide.with(img).load(imgPath).into(img).clearOnDetach()
            desc.text = imgDesc
        } else {
            desc.text = getString(R.string.brocken_img_path)
        }
        return fragmentView
    }
}