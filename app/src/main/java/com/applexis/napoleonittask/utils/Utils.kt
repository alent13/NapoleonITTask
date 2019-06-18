package com.applexis.napoleonittask.utils

import android.app.Activity
import android.app.Application
import android.os.Build

inline fun fromSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT >= version) f()
}

inline fun ifSdk(version: Int, f: () -> Unit) { //whenSdk?
    if (Build.VERSION.SDK_INT == version) f()
}

fun checkApplication(activity: Activity): Application {
    return activity.application ?: throw IllegalStateException(
        "Your activity/fragment is not yet attached to "
                + "Application. You can't request ViewModelsWithStateFactory "
                + "before onCreate call."
    )
}