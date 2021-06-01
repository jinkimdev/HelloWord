package dev.jinkim.android.helloword.notification

import android.content.Context
import androidx.work.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

/**
 * Schedule a periodic work to fetch word of the day and post a notification via WorkManager.
 */
class NotificationScheduler(context: Context) {

    private val workManager = WorkManager.getInstance(context)
    private val workRequest: PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<NotificationWorker>(12, TimeUnit.HOURS)
//            .setInitialDelay(diffInSeconds, TimeUnit.SECONDS)
            .setInitialDelay(10, TimeUnit.SECONDS) // For testing
            .addTag(NotificationWorker.TAG)
            .build()

    fun schedulePeriodicWork() {
        // Calculate time between now and the next time (morning) to run this work.
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

        workManager.enqueueUniquePeriodicWork(
            NotificationWorker.TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    private companion object {
        const val NOTIFICATION_HOUR = 8
    }
}