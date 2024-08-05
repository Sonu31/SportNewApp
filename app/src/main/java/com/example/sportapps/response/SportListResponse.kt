package com.example.sportapps.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class SportListResponse(
    @PrimaryKey(autoGenerate = true)
    val data: List<Data>,
    val message: String,
    val status: String,
    val statusCode: Int
)