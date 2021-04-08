package dev.jinkim.android.helloword.model.network

import com.squareup.moshi.Json

data class WordOfTheDay(
    @Json(name = "_id")
    val id: String = "",
    val word: String = "",
    val contentProvider: WordContentProvider = WordContentProvider(),
    val definitions: List<WordDefinition> = listOf(),
    val publishDate: String = "",
    val examples: List<WordExample> = listOf(),
    val pdd: String = "",
    val note: String = "",
    // TODO: 4/11/21 Need to see an example value
//    val htmlExtra: Any = Any()
) {

    data class WordContentProvider(
        val name: String = "",
        val id: Int = 0
    )

    data class WordDefinition(
        val source: String = "",
        val text: String = "",
        val note: String = "",
        val partOfSpeech: String = ""
    )

    data class WordExample(
        val url: String = "",
        val title: String = "",
        val text: String = "",
        val id: Int = 0
    )
}
