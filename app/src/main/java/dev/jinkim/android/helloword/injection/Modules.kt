package dev.jinkim.android.helloword.injection

import dev.jinkim.android.helloword.data.WordsRepository
import dev.jinkim.android.helloword.network.*
import dev.jinkim.android.helloword.ui.main.MainFragment
import dev.jinkim.android.helloword.ui.main.MainViewModel
import org.koin.dsl.module

object Modules {

    val wordsRepositoryModule = module {
        factory { WordsRepository(get()) }
    }

    val networkModule = module {
        single { provideRetrofit(get()) }

        factory { AuthInterceptor() }
        factory { provideOkHttpClient(get(), get()) }
        factory { provideWordApi(get()) }
        factory { provideLoggingInterceptor() }
//    factory { ResponseHandler() }
    }

    val fragmentModule = module {
        factory { MainFragment() }
    }

    val viewModelModule = module {
        factory { MainViewModel(get()) }
    }

}