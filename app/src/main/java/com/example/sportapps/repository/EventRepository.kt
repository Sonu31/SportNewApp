package com.example.sportapps.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportapps.api.EventService
import com.example.sportapps.db.EventDatabase
import com.example.sportapps.response.Data
import com.example.sportapps.utils.NetworkUtils

class EventRepository(
    private val eventService: EventService,
    private val eventDatabase: EventDatabase,
    private val applicationContext: Context
) {

    private val eventMutableLiveData = MutableLiveData<List<Data>>()

    val eventLivedata: LiveData<List<Data>>
        get() = eventMutableLiveData


    private val eventFilterMutableLiveData = MutableLiveData<List<Data>>()

    val eventFilterLivedata: LiveData<List<Data>>
        get() = eventFilterMutableLiveData

    suspend fun delete(item: Data) {
        eventDatabase.eventDao().delete(item)
    }

    suspend fun getEvents() {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            // API set
            val result = eventService.getEvents()
            val eventResponse = result.body()




            if (eventResponse != null) {
                val existingEvents = eventDatabase.eventDao().getEvents()
                val newEvents = eventResponse.data

                // Compare existing events with new events
                if (existingEvents != newEvents) {


                    // Update the database only if the new events are different from the existing ones
                    eventDatabase.eventDao().deleteAllEvents()

                    newEvents.forEach { event ->
                        eventDatabase.eventDao().addEvent(event)
                    }
                    eventMutableLiveData.postValue(newEvents)
                }else{
                    eventMutableLiveData.postValue(newEvents)
                }
            }
        } else {
            // DB calling
            val events = eventDatabase.eventDao().getEvents()
            eventMutableLiveData.postValue(events)
        }
    }


    suspend fun getFilterData(filterData: String) {
        val eventsBySport = eventDatabase.eventDao().getEventsBySport(filterData)
        eventFilterMutableLiveData.postValue(eventsBySport)

    }
}