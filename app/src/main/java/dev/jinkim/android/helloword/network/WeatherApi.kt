package dev.jinkim.android.helloword.network

import dev.jinkim.android.helloword.model.network.Weather
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?q=Helsinki&units=metric")
    suspend fun getForecast(): Weather
}
