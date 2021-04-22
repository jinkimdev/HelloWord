package dev.jinkim.android.helloword.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.jinkim.android.helloword.R
import dev.jinkim.android.helloword.data.WordsRepository
import dev.jinkim.android.helloword.model.network.WordOfTheDay
import dev.jinkim.android.helloword.util.DateUtil
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(private val wordsRepo: WordsRepository) : ViewModel() {
    var selectedDateString = MutableLiveData(DateUtil.getCurrentDate())

    val wordOfTheDay: LiveData<WordOfTheDay> = liveData {
        val w = wordsRepo.getWordOfTheDay(DateUtil.getCurrentDate())
        Log.d("MainViewModel", "Word of the day: $w.")
        emit(w)
    }

    fun getTitleString(context: Context, dateString: String): String {
        return context.getString(R.string.title_date, dateString)
    }
}
