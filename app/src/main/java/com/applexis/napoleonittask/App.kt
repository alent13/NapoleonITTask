package com.applexis.napoleonittask

import android.app.Application
import com.applexis.napoleonittask.data.DBService
import com.applexis.napoleonittask.data.offer.OfferRepository
import com.applexis.napoleonittask.offers.OfferListViewModelFactory
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App() : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<SqlDriver>() with singleton { AndroidSqliteDriver(Database.Schema, applicationContext, "offers.db") }
        bind<Database>() with singleton { Database(instance()) }
        bind<DBService>() with singleton { DBService(instance()) }
        bind<OfferRepository>() with singleton { OfferRepository(instance()) }
        bind() from provider { OfferListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}