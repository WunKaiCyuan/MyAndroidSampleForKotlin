package com.cyuan.mysampleforkotlin.sample.instagram.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cyuan.mysampleforkotlin.sample.instagram.ui.camera.CameraFragment
import com.cyuan.mysampleforkotlin.sample.instagram.ui.direct.DirectFragment
import com.cyuan.mysampleforkotlin.sample.instagram.ui.home.HomeFragment

class ContainerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    val list = listOf(
        CameraFragment(),
        HomeFragment(),
        DirectFragment()
    )

    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }

}