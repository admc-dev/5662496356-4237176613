package com.admcdev.crypto.techtest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface CurrencyDao {

    @Insert
    fun insertAll(currencyInfos: List<CurrencyInfo>)

    @Query("select * from currencyTable")
    fun getAllCurrencies(): Observable<List<CurrencyInfo>>
}