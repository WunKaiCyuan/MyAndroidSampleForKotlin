package com.cyuan.mysampleforkotlin.sample.instagram.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyuan.mysampleforkotlin.R
import kotlinx.android.synthetic.main.activity_instagram_main.*

class InstagramMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram_main)

        vpContainer.adapter =
            ContainerAdapter(
                supportFragmentManager
            )
        vpContainer.setCurrentItem(1)
    }
}