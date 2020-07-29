package com.cyuan.mysampleforkotlin.main

import com.cyuan.mysampleforkotlin.sample.instagram.ui.main.InstagramMainActivity
import com.cyuan.mysampleforkotlin.sample.video.VideoActivity

class MainRepository {

    fun loadSampleInfoList(): List<SampleInfo<*>> {
        val data = listOf<SampleInfo<*>>(
            SampleInfo(
                "2020/07/26 實作影片撥放器",
                SampleInfo.STATUS_FINISH,
                VideoActivity::class.java
            ),
            SampleInfo(
                "2020/07/28 實作InstagramApp",
                SampleInfo.STATUS_FINISH,
                InstagramMainActivity::class.java
            )
        )

        return data
    }

}