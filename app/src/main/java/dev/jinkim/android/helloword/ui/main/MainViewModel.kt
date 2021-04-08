package dev.jinkim.android.helloword.ui.main

import androidx.lifecycle.ViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel() }
}

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}