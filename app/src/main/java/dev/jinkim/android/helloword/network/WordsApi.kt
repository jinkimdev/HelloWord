package dev.jinkim.android.helloword.network

import dev.jinkim.android.helloword.model.network.WordOfTheDay
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApi {
    @GET("v4/words.json/wordOfTheDay")
    suspend fun getWordOfTheDay(@Query("date") date: String): WordOfTheDay
}
