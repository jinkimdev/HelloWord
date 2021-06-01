package dev.jinkim.android.helloword

import android.app.Application
import dev.jinkim.android.helloword.injection.Modules.fragmentModule
import dev.jinkim.android.helloword.injection.Modules.networkModule
import dev.jinkim.android.helloword.injection.Modules.notificationSchedulerModule
import dev.jinkim.android.helloword.injection.Modules.viewModelModule
import dev.jinkim.android.helloword.injection.Modules.wordsRepositoryModule
import dev.jinkim.android.helloword.notification.NotificationScheduler
import dev.jinkim.android.helloword.notification.WordFetchWorker
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.dsl.worker
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class HelloWordApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workerModule = module {
            worker { WordFetchWorker(get(), get(), get(), get()) }
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@HelloWordApp)
            // Custom WorkManager init to use with Koin
            workManagerFactory()
            modules(listOf(wordsRepositoryModule, networkModule, notificationSchedulerModule, fragmentModule, viewModelModule, workerModule))
        }

        // Retrieve NotificationScheduler from Koin and schedule periodic work for notifications
        val notificationScheduler = getKoin().get<NotificationScheduler>()
        notificationScheduler.schedulePeriodicFetchWork()
    }

}