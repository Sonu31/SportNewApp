package com.example.sportapps.ui.mainScreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapps.repository.EventRepository
import com.example.sportapps.response.Data
import kotlinx.coroutines.launch

class MainViewModel(private val repository: EventRepository) : ViewModel() {
    suspend fun getEvent(){
        repository.getEvents()
    }
  suspend fun getFilterEvent(filterValue:String){
        repository.getFilterData(filterValue)
    }
    val eventLivedata : LiveData<List<Data>>
    get() = repository.eventLivedata

    val eventFilterLivedata : LiveData<List<Data>>
    get() = repository.eventFilterLivedata


    fun delete(item: Data) = viewModelScope.launch {
        repository.delete(item)
    }
}