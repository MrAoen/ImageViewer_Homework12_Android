package com.example.imageviewer_homework12_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageviewer_homework12_android.adapters.ClickHandler
import com.example.imageviewer_homework12_android.adapters.ImageReyclerViewAdapter
import com.example.imageviewer_homework12_android.model.ImageItem

class ImageListFragment : Fragment(), ClickHandler {
    private lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_image_list, container, false)

        rcView = fragmentView.findViewById(R.id.rcView)
        rcView.layoutManager = LinearLayoutManager(requireContext())
        rcView.adapter = ImageReyclerViewAdapter(App.imgList, this)
        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        rcView.adapter?.notifyDataSetChanged()
    }

    override fun itemClick(holder: ImageItem) {

        parentFragmentManager.commit {
            val imgFragment = ImageFragment()
            imgFragment.arguments = bundleOf(
                "img_path" to holder.imageUrl,
                "img_description" to holder.description
            )
            replace(R.id.container, imgFragment).addToBackStack(null)
        }

    }
}