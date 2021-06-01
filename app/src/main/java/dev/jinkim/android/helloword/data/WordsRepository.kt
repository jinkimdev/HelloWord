package dev.jinkim.android.helloword.data

import dev.jinkim.android.helloword.model.network.WordOfTheDay
import dev.jinkim.android.helloword.network.WordsApi

class WordsRepository(private val wordsApi: WordsApi) {

    /**
     * Ex) "yyyy-MM-dd"
     * // TODO: Read from cache
     */
    suspend fun getWordOfTheDay(dateString: String): WordOfTheDay = wordsApi.getWordOfTheDay(dateString)
}
