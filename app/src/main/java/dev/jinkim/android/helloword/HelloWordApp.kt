package dev.jinkim.android.helloword

import android.app.Application
import dev.jinkim.android.helloword.injection.Modules.fragmentModule
import dev.jinkim.android.helloword.injection.Modules.networkModule
import dev.jinkim.android.helloword.injection.Modules.viewModelModule
import dev.jinkim.android.helloword.injection.Modules.wordsRepositoryModule
import dev.jinkim.android.helloword.notification.NotificationScheduler
import dev.jinkim.android.helloword.notification.NotificationWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

class HelloWordApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workerModule = module {
            worker { NotificationWorker(get(), get(), get()) }
        }

        startKoin {
            androidContext(this@HelloWordApp)
            // Custom WorkManager init to use with Koin
            workManagerFactory()
            modules(listOf(wordsRepositoryModule, networkModule, fragmentModule, viewModelModule, workerModule))
        }

        // Schedule periodic work for notifications
        val notificationScheduler = NotificationScheduler(this)
        notificationScheduler.schedulePeriodicWork()
    }

}