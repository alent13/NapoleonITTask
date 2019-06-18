package com.applexis.napoleonittask.data.offer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applexis.napoleonittask.data.DBService
import com.applexis.napoleonittask.data.Source
import com.applexis.napoleonittask.data.WebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OfferRepository(val dbService: DBService) {

    fun getOfferList(uiScope: CoroutineScope, callback: (List<OfferInfo>, source: Source) -> Unit) {
        callback(dbService.getOfferList(), Source.Local)

        loadOfferList(uiScope, callback)
    }

    fun loadOfferList(uiScope: CoroutineScope, callback: (List<OfferInfo>, source: Source) -> Unit) {
        uiScope.launch {
            try {
                val list = withContext(Dispatchers.IO) { WebService.getOfferList() }
                dbService.saveAll(list)
                callback(list, Source.Remote)
            } catch (ex: Exception) {
                Log.d("TAG", ex.message)
            }
        }
    }

}