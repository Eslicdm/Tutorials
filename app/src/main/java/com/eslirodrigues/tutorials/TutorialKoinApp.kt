package com.eslirodrigues.tutorials

import android.app.Application
import com.eslirodrigues.tutorials.di_koin.di.mainModule
import com.eslirodrigues.tutorials.di_koin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TutorialKoinApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TutorialKoinApp)
            modules(mainModule, viewModelModule)
        }
    }
}