package dev.jinkim.android.helloword.notification

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Worker to post a notification with a daily word.
 */
class NotificationPostWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        // Time to post a notification to the system.
        // TODO: 6/1/21 Notification
        Log.d(TAG, "Post a notification.")
        return Result.success()
    }

    companion object {
        const val TAG = "NotificationPostWorker"
    }
}
