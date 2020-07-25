package com.cyuan.mysampleforkotlin.main

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

class SampleInfo<T : AppCompatActivity>(
    val displayName: String,
    val status: Int,
    val activity: Class<T>
) {

    companion object {
        const val STATUS_INIT = 1
        const val STATUS_PADDING = 2
        const val STATUS_FINISH = 3
    }
}