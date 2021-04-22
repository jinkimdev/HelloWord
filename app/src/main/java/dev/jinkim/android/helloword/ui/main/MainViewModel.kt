package dev.jinkim.android.helloword.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import dev.jinkim.android.helloword.R
import dev.jinkim.android.helloword.data.WordsRepository
import dev.jinkim.android.helloword.util.DateUtil
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(private val wordsRepo: WordsRepository) : ViewModel() {
    var selectedDateString = MutableLiveData(DateUtil.getCurrentDate())

    fun getWordOfTheDay(date: String) = liveData(Dispatchers.IO) {
        val word = wordsRepo.getWordOfTheDay(date)
        Log.d("MainViewModel", "Word of the day fetched: $word")
        emit(word)
    }

    fun getTitleString(context: Context, dateString: String): String {
        return context.getString(R.string.hw_title_date, dateString)
    }
}
