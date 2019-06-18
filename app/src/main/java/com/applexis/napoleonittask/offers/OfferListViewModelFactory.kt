package com.applexis.napoleonittask.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applexis.napoleonittask.data.offer.OfferRepository

class OfferListViewModelFactory(private val offerRepository: OfferRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress(names = ["UNCHECKED_CAST"])
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = OfferListViewModel(offerRepository) as T

}