package com.applexis.napoleonittask.data.offer

import com.applexis.napoleonittask.data.Offer

data class OfferInfo(
    val id: String,
    val name: String,
    val desc: String?,
    val groupName: String,
    val type: String,
    val image: String,
    val price: Int?,
    val discount: Float?
) {

    companion object {
        val promotion = "product"
    }

    constructor(offer: Offer) : this(
        offer.id,
        offer.name,
        offer.desc,
        offer.groupName,
        offer.type,
        offer.image,
        offer.price,
        offer.discount
    )

}