package com.applexis.napoleonittask.utils

import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout

fun TabLayout.reduceMarginsInTabs(marginOffset: Int) {
    val tabStrip = this.getChildAt(0)
    if (tabStrip is ViewGroup) {
        for (i in 0 until tabStrip.childCount) {
            val tabView = tabStrip.getChildAt(i)
            if (tabView.layoutParams is ViewGroup.MarginLayoutParams) {
                (tabView.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = marginOffset
                (tabView.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = marginOffset
            }
        }

        this.requestLayout()
    }
}