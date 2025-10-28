package com.example.tendederoapp.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current") val current: CurrentData
)

data class CurrentData(
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("probability") val probability: ProbabilityData? = null
)

data class ProbabilityData(
    @SerializedName("precipitation") val precipitation: Int
)