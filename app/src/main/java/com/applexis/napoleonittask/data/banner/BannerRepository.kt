package com.applexis.napoleonittask.data.banner

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.applexis.napoleonittask.data.WebService
import kotlinx.coroutines.*

object BannerRepository {

    fun getBannerList(uiScope: CoroutineScope, callback: (List<BannerInfo>) -> Unit) {
        uiScope.launch {
            try {
                callback(withContext(Dispatchers.IO) { WebService.getBannerList() })
            } catch (ex: Exception) {
                Log.d("TAG", ex.message)
            }
        }
    }


}