package com.admcdev.crypto.techtest.viewmodels

import com.admcdev.crypto.techtest.data.CurrencyDao
import com.admcdev.crypto.techtest.data.CurrencyInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class CurrenciesViewModelTest {

    private val currencyDao: CurrencyDao = mock()

    private lateinit var currenciesViewModel: CurrenciesViewModel

    private val currencyListUnordered = listOf(
        CurrencyInfo(
            "B",
            "BB",
            "BBB"
        ),
        CurrencyInfo(
            "A",
            "AA",
            "AAA"
        )

    )

    private val currencyListOrdered = listOf(
        CurrencyInfo(
            "A",
            "AA",
            "AAA"
        ),
        CurrencyInfo(
            "B",
            "BB",
            "BBB"
        ),
    )

    @Before
    fun setup() {
        whenever(currencyDao.getAllCurrencies()).thenReturn(
            Observable.just(
                currencyListUnordered
            )
        )

        currenciesViewModel = CurrenciesViewModel(currencyDao)
    }

    @Test
    fun return_list_unordered_by_default() {
        currenciesViewModel.currencyList.test()
            .assertValue(currencyListUnordered)
            .dispose()
    }

    @Test
    fun return_list_ordered_when_set() {
        currenciesViewModel.sortClicked(CurrenciesViewModel.SortType.ATOZ)

        currenciesViewModel.currencyList.test()
            .assertValue(currencyListOrdered)
            .dispose()
    }

    @Test
    fun return_list_ordered_when_set_try_back() {
        currenciesViewModel.sortClicked(CurrenciesViewModel.SortType.ATOZ)
        currenciesViewModel.sortClicked(CurrenciesViewModel.SortType.NONE)

        currenciesViewModel.currencyList.test()
            .assertValue(currencyListUnordered)
            .dispose()
    }
}