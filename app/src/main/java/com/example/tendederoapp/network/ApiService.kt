package com.example.tendederoapp.network

import com.example.tendederoapp.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept: application/json")
    @GET("point")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("sections") sections: String,
        @Query("key") apiKey: String
    ): WeatherResponse
}