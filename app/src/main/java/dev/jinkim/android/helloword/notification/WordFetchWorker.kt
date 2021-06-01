package dev.jinkim.android.helloword.notification

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.jinkim.android.helloword.data.WordsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Worker to fetch from "word of the day" API to get today's data.
 */
class WordFetchWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val wordsRepo: WordsRepository,
    private val scheduler: NotificationScheduler
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "Worker runs - fetch word of the day.")
            val word = wordsRepo.getWordOfTheDay("2021-05-30")
            Log.d(TAG, "Word of the day: ${word.word} (${word.publishDate}): ${word.definitions}")
            scheduler.scheduleNotificationPostWork()
            return@withContext Result.success()
        }
    }

    companion object {
        const val TAG = "WordFetchWorker"
    }
}
