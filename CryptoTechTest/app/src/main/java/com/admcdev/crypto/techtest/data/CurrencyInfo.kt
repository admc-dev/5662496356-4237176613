package com.admcdev.crypto.techtest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyTable")
data class CurrencyInfo(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "symbol")
    val symbol: String
)