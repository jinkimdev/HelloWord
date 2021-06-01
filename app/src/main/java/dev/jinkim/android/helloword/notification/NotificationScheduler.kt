package dev.jinkim.android.helloword.notification

import android.content.Context
import android.util.Log
import androidx.work.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

/**
 * Schedule a periodic work to fetch word of the day and post a notification via WorkManager.
 */
class NotificationScheduler(context: Context) {

    private val workManager = WorkManager.getInstance(context)

    /**
     * Schedule a periodic work to fetch from "word of the day" API daily.
     */
    fun schedulePeriodicFetchWork() {
        val initialDelaySec = getInitialDelaySecFromNow(PERIODIC_FETCH_HOUR)
        val workRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<WordFetchWorker>(12, TimeUnit.HOURS)
//            .setInitialDelay(initialDelaySec, TimeUnit.SECONDS)
                .setInitialDelay(10, TimeUnit.SECONDS) // For testing
                .addTag(WordFetchWorker.TAG)
                .build()

        workManager.enqueueUniquePeriodicWork(
            WordFetchWorker.TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    /**
     * Schedule a one-time work to post a notification at 8AM.
     */
    fun scheduleNotificationPostWork() {
        val initialDelaySec = getInitialDelaySecFromNow(NOTIFICATION_HOUR)
        val workRequest = OneTimeWorkRequestBuilder<NotificationPostWorker>()
            .setInitialDelay(initialDelaySec, TimeUnit.SECONDS)
            .addTag(NotificationPostWorker.TAG)
            .build()
        workManager.enqueueUniqueWork(
            NotificationPostWorker.TAG,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        Log.d(TAG, "Schedule a one-time worker to post a notification with an initial delay of ($initialDelaySec) seconds.")
    }

    private fun getInitialDelaySecFromNow(hour: Int): Long {
        // Calculate time between now and the next time (morning) to run this work.
        val now = LocalDateTime.now()
        val fetchTime = now.withHour(hour)
            .withMinute(0)
            .withSecond(0)
            .let {
                if (now.hour >= hour) {
                    // If past 8AM, set it to the next day
                    it.plusDays(1)
                } else {
                    it
                }
            }

        return fetchTime.toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)
    }

    private companion object {
        const val TAG = "NotificationScheduler"
        const val PERIODIC_FETCH_HOUR = 5
        const val NOTIFICATION_HOUR = 8
    }
}