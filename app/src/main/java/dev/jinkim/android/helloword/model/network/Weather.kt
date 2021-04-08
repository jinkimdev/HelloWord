package dev.jinkim.android.helloword.model.network

import com.squareup.moshi.Json

data class Weather(
//    @Json(name = "main")
    val main: TempData,
    val name: String
) {
    data class TempData(
        val temp: Double,
        val humidity: Int
    )
}
