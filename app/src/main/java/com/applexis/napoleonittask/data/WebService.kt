package com.applexis.napoleonittask.data

import com.applexis.napoleonittask.data.banner.BannerInfo
import com.applexis.napoleonittask.data.offer.OfferInfo
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get

object WebService {

    private const val BASE_URL = "https://s3.eu-central-1.amazonaws.com/sl.files"
    private const val GET_BANNER_LIST = "$BASE_URL/banners.json"
    private const val GET_OFFER_LIST = "$BASE_URL/offers.json"

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        expectSuccess = true
    }

    suspend fun getBannerList(): List<BannerInfo> = client.get(GET_BANNER_LIST)

    suspend fun getOfferList(): List<OfferInfo> = client.get(GET_OFFER_LIST)

}