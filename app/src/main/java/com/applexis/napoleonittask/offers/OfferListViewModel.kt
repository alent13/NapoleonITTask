package com.applexis.napoleonittask.offers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applexis.napoleonittask.data.Source
import com.applexis.napoleonittask.data.banner.BannerInfo
import com.applexis.napoleonittask.data.banner.BannerRepository
import com.applexis.napoleonittask.data.offer.OfferInfo
import com.applexis.napoleonittask.data.offer.OfferRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelChildren

class OfferListViewModel(private val offerRepository: OfferRepository) : ViewModel() {

    val uiScope = MainScope()

    val bannerList: MutableLiveData<List<BannerInfo>> = MutableLiveData(listOf())
    val offerList: MutableLiveData<List<OfferInfo>> = MutableLiveData(listOf())
    val isOfferListLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isBannerListLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun refreshOfferList() {
        isOfferListLoading.postValue(true)
        offerRepository.loadOfferList(uiScope) { newOfferList, _ ->
            offerList.postValue(newOfferList)
            isOfferListLoading.postValue(false)
        }
    }

    fun loadData() {
        loadBannerList()
        loadOfferList()
    }

    fun loadBannerList() {
        isBannerListLoading.postValue(true)
        BannerRepository.getBannerList(uiScope) { newBannerList ->
            bannerList.postValue(newBannerList)
            isBannerListLoading.postValue(false)
        }
    }

    fun loadOfferList() {
        isOfferListLoading.postValue(true)
        offerRepository.getOfferList(uiScope) { newOfferList, source ->
            if (source == Source.Remote) {
                isOfferListLoading.postValue(false)
            }
            offerList.postValue(newOfferList)
        }
    }

    override fun onCleared() {
        uiScope.coroutineContext.cancelChildren()
    }

}