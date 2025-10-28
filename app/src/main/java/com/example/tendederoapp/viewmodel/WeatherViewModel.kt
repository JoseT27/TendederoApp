package com.example.tendederoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tendederoapp.data.WeatherResponse
import com.example.tendederoapp.network.ApiClient
import com.example.tendederoapp.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchWeatherData(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getCurrentWeather(
                    lat = lat,
                    lon = lon,
                    sections = "current",
                    apiKey = ApiClient.API_KEY
                )
                _weatherData.value = response
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener los datos del clima: ${e.message}"
                _weatherData.value = null
            }
        }
    }
}