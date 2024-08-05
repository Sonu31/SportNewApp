package com.example.sportapps.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sportapps.response.Data


@Dao
interface EventDAO {

    @Insert
     fun addEvent(event: Data)

    @Query("SELECT * FROM events")
     fun getEvents(): List<Data>

    @Delete
    suspend fun delete(item:Data)

    @Query("DELETE FROM events")
    fun deleteAllEvents()

    @Query("SELECT * FROM events WHERE sport_name = :sport")
    fun getEventsBySport(sport: String): List<Data>
}