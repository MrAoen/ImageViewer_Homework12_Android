package com.example.imageviewer_homework12_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.imageviewer_homework12_android.R.drawable.photo_camera
import com.example.imageviewer_homework12_android.R.drawable.image_list
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val pager = findViewById<ViewPager2>(R.id.pager)

        pager.adapter = MyPagerAdapter(this)
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }
        })
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.images)
                    tab.icon = AppCompatResources.getDrawable(applicationContext,image_list)
                    val bage = tab.orCreateBadge
                    bage.number = App.imgList.size
                }
                1 -> {
                    tab.text = getString(R.string.get_foto)
                    tab.icon = AppCompatResources.getDrawable(applicationContext,photo_camera)
                }
            }
        }.attach()
    }
}

class MyPagerAdapter(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ImageListFragment()
            1 -> CameraFragment()
            else -> throw IllegalArgumentException("strange position $position hen selecting tabs")
        }
    }
}