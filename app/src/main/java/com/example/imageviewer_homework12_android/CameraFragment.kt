package com.example.imageviewer_homework12_android

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.imageviewer_homework12_android.model.ImageItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import java.io.File
import java.util.*

class CameraFragment : Fragment() {

    val authority = "com.example.imageviewer_homework12_android.fileprovider"
    var currentPhotoPath: String? = null
    private lateinit var imageView: ImageView
    private lateinit var descriptionField: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_camera, container, false)

        descriptionField = fragmentView.findViewById(R.id.description)
        imageView = fragmentView.findViewById(R.id.camera)
        val cameraBtn = fragmentView.findViewById<Button>(R.id.take_pic)
        cameraBtn?.setOnClickListener {
            takePicture()
        }
        val saveBtn = fragmentView.findViewById<Button>(R.id.save_pic)
        saveBtn.setOnClickListener {
            saveImg()
        }

        return fragmentView
    }

    private fun saveImg() {
        if (descriptionField.text.isEmpty()) {
            Snackbar.make(requireView(),"The description is needed!!!",Snackbar.LENGTH_LONG).show()
        } else {
            App.imgList.add(
                ImageItem(
                    currentPhotoPath ?: "wrong path",
                    descriptionField.text.toString()
                )
            )
            savePrefs()
            val tab = requireActivity().findViewById<TabLayout>(R.id.tabLayout)
            val bage = tab.getTabAt(0)?.orCreateBadge
            bage?.number = App.imgList.size
        }
    }

    private val contract = ActivityResultContracts.StartActivityForResult()
    private val resultLauncher = registerForActivityResult(contract) {
        if (it.resultCode == RESULT_OK) {
            Glide.with(this).load(currentPhotoPath).into(imageView)
        }
    }

    private fun takePicture() {
        val randomName = UUID.randomUUID().toString()
        val fileDir = context?.getExternalFilesDir("pic")
        val file = File.createTempFile(randomName, ".jpg", fileDir).apply {
            currentPhotoPath = absolutePath
        }
        val photoURI = FileProvider.getUriForFile(requireContext(), authority, file)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        }
        resultLauncher.launch(intent)
    }

    fun savePrefs() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefs.edit().apply() {
            App.imgList.forEach {
                putString(it.imageUrl, it.description)
            }
        }.apply()
    }
}

