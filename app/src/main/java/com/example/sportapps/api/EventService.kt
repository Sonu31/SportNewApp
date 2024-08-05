package com.example.sportapps.api

import com.example.sportapps.response.SportListResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventService {
    @GET("api/mock/get_all_sport_list")
    suspend fun getEvents() : Response<SportListResponse>
}