package dev.jinkim.android.helloword.notification

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.jinkim.android.helloword.data.WordsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val wordsRepo: WordsRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "Worker runs - fetch word of the day.")
            val word = wordsRepo.getWordOfTheDay("2021-05-30")
            Log.d(TAG, "Word of the day: ${word.word} (${word.publishDate}): ${word.definitions}")

            // TODO: 6/1/21 Schedule a one-time work to post a notification at 8AM.

            return@withContext Result.success()
        }
    }

    companion object {
        const val TAG = "NotificationWorker"
    }
}
