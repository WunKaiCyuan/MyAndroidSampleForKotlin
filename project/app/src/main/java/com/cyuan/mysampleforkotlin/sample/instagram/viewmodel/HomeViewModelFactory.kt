package com.cyuan.mysampleforkotlin.sample.instagram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cyuan.mysampleforkotlin.sample.instagram.repository.HomeRepository

class HomeViewModelFactory(val repository: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else {
            throw IllegalAccessException("Unknown ViewModel class")
        }
    }
}