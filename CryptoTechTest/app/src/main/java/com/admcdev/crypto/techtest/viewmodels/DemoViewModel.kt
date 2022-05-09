package com.admcdev.crypto.techtest.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DemoViewModel : ViewModel() {

    val activeFragment = MutableLiveData(FragmentID.LANDING)
    val sortCurrenciesVisibility = MutableLiveData(View.GONE)
    val resetCurrenciesVisibility = MutableLiveData(View.GONE)

    fun showCurrencyClick() {
        sortCurrenciesVisibility.value = View.VISIBLE
        resetCurrenciesVisibility.value = View.VISIBLE
        activeFragment.value = FragmentID.CURRENCIES
    }

    fun backPressed(): Boolean {
        if (activeFragment.value == FragmentID.CURRENCIES) {
            sortCurrenciesVisibility.value = View.GONE
            resetCurrenciesVisibility.value = View.GONE
            activeFragment.value = FragmentID.LANDING
            return true
        }

        return false
    }

    enum class FragmentID {
        LANDING,
        CURRENCIES
    }
}