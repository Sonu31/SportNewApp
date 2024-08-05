package com.example.sportapps.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val nsrs_sport_id: Int?,
    val rf_sport_db_name: String?,
    val sport_id: Int?,
    val sport_name: String?,
    val status: String?
)
