package com.task.listofusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel: ViewModel() {
    private val dataSource = DataSource()

    fun getDataBySeed(seed: String, results: Long) = liveData(Dispatchers.IO){
        val data = dataSource.getDataBySeed(seed, results)
        emit(data)
    }

    fun getData() = liveData(Dispatchers.IO){
        val data = dataSource.getData()
        emit(data)
    }
}