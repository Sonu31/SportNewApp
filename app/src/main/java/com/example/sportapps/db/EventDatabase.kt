package com.example.sportapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.example.sportapps.response.Data

@Database(entities = [Data::class], version = 3)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDAO

    companion object {
        private var INSTANCE: EventDatabase? = null
        fun getDatabase(context: Context): EventDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        EventDatabase::class.java,
                        "sport_Databases"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}