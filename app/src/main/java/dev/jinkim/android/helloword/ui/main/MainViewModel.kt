package dev.jinkim.android.helloword.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.jinkim.android.helloword.data.WordsRepository
import dev.jinkim.android.helloword.model.network.WordOfTheDay
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(private val wordsRepo: WordsRepository) : ViewModel() {
    val wordOfTheDay: LiveData<WordOfTheDay> = liveData {
        val w = wordsRepo.getWordOfTheDay()
        Log.d("MainViewModel", w.toString())
        emit(w)
    }
}
