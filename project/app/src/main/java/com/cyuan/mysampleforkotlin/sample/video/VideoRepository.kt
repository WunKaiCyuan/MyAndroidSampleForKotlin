package com.cyuan.mysampleforkotlin.sample.video

import android.net.Uri

class VideoRepository {
    fun loadVideoList(): List<VideoInfo> {
        val data = listOf(
            VideoInfo(
                Uri.parse("https://firebasestorage.googleapis.com/v0/b/myandroidsample-6ae8d.appspot.com/o/Video%2FLINE_MOVIE_1594381868498.mp4?alt=media&token=43fa1bd0-42c9-44fc-a936-110d422496aa"),
                "姆姆子呼嚕",
                "宥新仔"
            ),
            VideoInfo(
                Uri.parse("https://firebasestorage.googleapis.com/v0/b/myandroidsample-6ae8d.appspot.com/o/Video%2FLINE_MOVIE_1594550865034.mp4?alt=media&token=d7aad67f-2111-4dfc-8370-172053006b5e"),
                "姆姆子吃飯",
                "志紘仔"
            ),
            VideoInfo(
                Uri.parse("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4"),
                "橫向影片",
                "未知提供者"
            )
        )

        return data
    }
}