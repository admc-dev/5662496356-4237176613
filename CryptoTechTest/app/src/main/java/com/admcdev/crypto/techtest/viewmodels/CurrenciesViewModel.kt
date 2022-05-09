package com.admcdev.crypto.techtest.viewmodels

import androidx.lifecycle.*
import com.admcdev.crypto.techtest.data.CurrencyDao
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class CurrenciesViewModel(currencyDao: CurrencyDao) : ViewModel() {

    private val _currencyList = currencyDao.getAllCurrencies()
    private val _sortType = BehaviorSubject.createDefault(SortType.NONE)

    val currencyList = Observable.combineLatest(_currencyList, _sortType) { list, sortType ->
        when (sortType) {
            SortType.ATOZ -> list.sortedBy { it.symbol }
            SortType.NONE -> list
        }
    }

    fun sortClicked(sortType: SortType) {
        _sortType.onNext(sortType)
    }

    enum class SortType {
        NONE,
        ATOZ
    }
}