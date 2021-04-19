package dev.jinkim.android.helloword.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getCurrentDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }
}