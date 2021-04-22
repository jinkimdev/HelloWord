package dev.jinkim.android.helloword.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getCurrentDate(): String {
        return getDateFormat().format(Calendar.getInstance(Locale.getDefault()).time)
    }

    private fun getDateFormat(): SimpleDateFormat {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getDefault()
        return format
    }
}