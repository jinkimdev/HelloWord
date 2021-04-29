package dev.jinkim.android.helloword.notification

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // TODO: Fetch word of the day and post a notification
        Log.d(TAG, "Worker runs - fetch word of the day and post a notification.")
        return Result.success()
    }

    companion object {
        const val TAG = "NotificationWorker"
    }
}