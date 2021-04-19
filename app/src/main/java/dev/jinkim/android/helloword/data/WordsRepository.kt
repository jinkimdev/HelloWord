package dev.jinkim.android.helloword.data

import dev.jinkim.android.helloword.network.WordsApi
import org.koin.dsl.module

val wordsModule = module {
    factory { WordsRepository(get()) }
}

class WordsRepository(private val wordsApi: WordsApi) {
    suspend fun getWordOfTheDay(dateString: String) = wordsApi.getWordOfTheDay(dateString)
}
