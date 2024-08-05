package com.example.sportapps

import android.app.Application
import com.example.sportapps.api.EventService
import com.example.sportapps.api.RetrofitHelper
import com.example.sportapps.db.EventDatabase
import com.example.sportapps.repository.EventRepository


class Application : Application() {

    lateinit var repository: EventRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(EventService::class.java)
        val database = EventDatabase.getDatabase(applicationContext)
        repository = EventRepository(quoteService, database, applicationContext)
    }
}