package dev.jinkim.android.helloword

import android.app.Application
import dev.jinkim.android.helloword.injection.Modules.fragmentModule
import dev.jinkim.android.helloword.injection.Modules.networkModule
import dev.jinkim.android.helloword.injection.Modules.viewModelModule
import dev.jinkim.android.helloword.injection.Modules.wordsRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HelloWordApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HelloWordApp)
            modules(listOf(wordsRepositoryModule, networkModule, fragmentModule, viewModelModule))
        }
    }

}