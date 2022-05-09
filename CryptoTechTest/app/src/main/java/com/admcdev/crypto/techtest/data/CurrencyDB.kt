package com.admcdev.crypto.techtest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class CurrencyDB : RoomDatabase() {
    abstract val currencyDao: CurrencyDao

    companion object {

        @Volatile
        private var INSTANCE: CurrencyDB? = null

        fun getInstance(context: Context): CurrencyDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context): CurrencyDB {
            val db = Room.databaseBuilder(
                context.applicationContext,
                CurrencyDB::class.java,
                "CURRENCY-DB"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        Executors.newSingleThreadScheduledExecutor().execute {
                            getInstance(context).currencyDao.insertAll(CURRENCY_DATA)
                        }
                    }
                })
                .build()

            Executors.newSingleThreadScheduledExecutor().execute {
                db.runInTransaction {
                }
            }

            return db
        }

        val CURRENCY_DATA = listOf(
            CurrencyInfo("BTC", "Bitcoin", "BTC"),
            CurrencyInfo("ETH", "Ethereum", "ETH"),
            CurrencyInfo("XRP", "XRP", "XRP"),
            CurrencyInfo("BCH", "Bitcoin Cash", "BCH"),
            CurrencyInfo("LTC", "Litecoin", "LTC"),
            CurrencyInfo("EOS", "EOS", "EOS"),
            CurrencyInfo("BNB", "Binance Coin", "BNB"),
            CurrencyInfo("LINK", "Chainlink", "LINK"),
            CurrencyInfo("NEO", "NEO", "NEO"),
            CurrencyInfo("ETC", "Ethereum Classic", "ETC"),
            CurrencyInfo("ONT", "Ontology", "ONT"),
            CurrencyInfo("CRO", "Crypto.com Chain", "CRO"),
            CurrencyInfo("CUC", "Cucumber", "CUC"),
            CurrencyInfo("USDC", "USD Coin", "USDC")
        )

    }
}