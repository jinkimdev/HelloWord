package dev.jinkim.android.helloword.data

import dev.jinkim.android.helloword.network.WordsApi

class WordsRepository(private val wordsApi: WordsApi) {
    suspend fun getWordOfTheDay(dateString: String) = wordsApi.getWordOfTheDay(dateString)
}
