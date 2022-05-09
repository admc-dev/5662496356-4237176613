package com.admcdev.crypto.techtest.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CryptoApplication)
            modules(
                currencyDB,
                viewModels,
            )
        }
    }
}