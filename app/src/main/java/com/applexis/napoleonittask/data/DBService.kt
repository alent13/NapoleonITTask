package com.applexis.napoleonittask.data

import com.applexis.napoleonittask.Database
import com.applexis.napoleonittask.data.offer.OfferInfo
import com.squareup.sqldelight.Transacter

class DBService(val database: Database) {

    fun getOfferList(): List<OfferInfo> =
        database.offerQueries.selectAll().executeAsList().map { offer -> OfferInfo(offer) }

    fun saveAll(list: List<OfferInfo>) {
        database.offerQueries.clear()
        list.forEach {
            database.offerQueries.save(it.id, it.name, it.desc, it.groupName, it.type, it.image, it.price, it.discount)
        }
    }

}