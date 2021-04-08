package dev.jinkim.android.helloword.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.jinkim.android.helloword.data.WeatherRepository
import dev.jinkim.android.helloword.model.network.Weather
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(private val weatherRepo: WeatherRepository) : ViewModel() {
    val weather: LiveData<Weather> = liveData {
        emit(weatherRepo.getWeather())
    }
}
