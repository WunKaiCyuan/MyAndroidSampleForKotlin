package com.cyuan.mysampleforkotlin.sample.video

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideoViewModelFactory(val application: Application, val repository: VideoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
            return VideoViewModel(application, repository) as T
        } else {
            throw IllegalAccessException("Unknown ViewModel class")
        }
    }
}