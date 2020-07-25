package com.cyuan.mysampleforkotlin.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyuan.mysampleforkotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    lateinit var sampleData: ArrayList<SampleInfo<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bind view model
        val mainRepository = MainRepository()
        val viewModelFactory = MainViewModelFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // set rvSampleList
        sampleData = arrayListOf()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = SampleListAdapter(sampleData)
        rvSampleList.layoutManager = layoutManager
        rvSampleList.adapter = adapter

        // load sample list data
        viewModel.loadSampleInfoList()
        viewModel.sampleInfoListLiveData.observe(this, Observer {
            sampleData.clear()
            sampleData.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}