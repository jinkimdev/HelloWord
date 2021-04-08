package dev.jinkim.android.helloword

import android.app.Application
import dev.jinkim.android.helloword.data.wordsModule
import dev.jinkim.android.helloword.network.networkModule
import dev.jinkim.android.helloword.ui.main.fragmentModule
import dev.jinkim.android.helloword.ui.main.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HelloWordApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HelloWordApp)
            modules(listOf(wordsModule, networkModule, fragmentModule, viewModelModule,))
        }
    }

}