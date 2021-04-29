package dev.jinkim.android.helloword.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

/**
 * Schedule a periodic work to fetch word of the day and post a notification via WorkManager.
 */
class NotificationScheduler(context: Context) {

    private val workManager = WorkManager.getInstance(context)
    private val workRequest: WorkRequest

    init {
        // Schedule work
        val now = LocalDateTime.now()
        val notificationTime = now.withHour(NOTIFICATION_HOUR)
            .withMinute(0)
            .withSecond(0)
            .apply {
                if (now.hour >= NOTIFICATION_HOUR) {
                    // If past 8AM, set it to the next day
                    this.plusDays(1)
                }
            }
        val diffInSeconds = notificationTime.toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)

        workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(12, TimeUnit.HOURS)
            .setInitialDelay(diffInSeconds, TimeUnit.SECONDS)
            .addTag(NotificationWorker.TAG)
            .build()
        workManager.enqueueUniquePeriodicWork(NotificationWorker.TAG, ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    private companion object {
        const val NOTIFICATION_HOUR = 8
    }
}