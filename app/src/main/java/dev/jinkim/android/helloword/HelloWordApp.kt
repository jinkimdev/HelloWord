package dev.jinkim.android.helloword

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HelloWordApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HelloWordApp)
            modules(listOf())
        }
    }

}