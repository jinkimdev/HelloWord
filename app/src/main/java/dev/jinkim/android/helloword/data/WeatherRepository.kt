package dev.jinkim.android.helloword.data

import dev.jinkim.android.helloword.network.WeatherApi
import org.koin.dsl.module

val forecastModule = module {
    factory { WeatherRepository(get()) }
}

class WeatherRepository(private val weatherApi: WeatherApi) {
    suspend fun getWeather() = weatherApi.getForecast()
}
