package com.admcdev.crypto.techtest.koin

import android.app.Application
import com.admcdev.crypto.techtest.data.CurrencyDB
import com.admcdev.crypto.techtest.data.CurrencyDao
import com.admcdev.crypto.techtest.viewmodels.CurrenciesViewModel
import com.admcdev.crypto.techtest.viewmodels.DemoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currencyDB = module {
    fun provideDataBase(application: Application): CurrencyDB {
        return CurrencyDB.getInstance(application)
    }

    fun provideDao(dataBase: CurrencyDB): CurrencyDao {
        return dataBase.currencyDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val viewModels = module {
    viewModel {
        DemoViewModel()
    }
    viewModel {
        CurrenciesViewModel(get())
    }
}