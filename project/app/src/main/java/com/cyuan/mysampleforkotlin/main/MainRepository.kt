package com.cyuan.mysampleforkotlin.main

import com.cyuan.mysampleforkotlin.sample.video.VideoActivity

class MainRepository {

    fun loadSampleInfoList(): List<SampleInfo<*>> {
        val data = listOf<SampleInfo<*>>(
            SampleInfo(
                "2020/07/26 影片控制器",
                SampleInfo.STATUS_FINISH,
                VideoActivity::class.java
            )
        )

        return data
    }

}