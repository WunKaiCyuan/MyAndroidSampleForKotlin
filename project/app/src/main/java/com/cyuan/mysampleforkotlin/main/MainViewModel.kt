package com.cyuan.mysampleforkotlin.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    val sampleInfoListLiveData = MutableLiveData<List<SampleInfo<*>>>()

    fun loadSampleInfoList() {
        val data = repository.loadSampleInfoList()
        sampleInfoListLiveData.value = data
    }
}