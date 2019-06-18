package com.applexis.napoleonittask.offers.adapters

import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.applexis.napoleonittask.R
import org.jetbrains.anko.*

class OfferTitleItemUI: AnkoComponent<ViewGroup> {

    lateinit var header: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            view {
                backgroundColorResource = R.color.offerDividerColor
            }.lparams(matchParent, dip(1))
            header = textView {
                this.gravity = Gravity.START.or(Gravity.CENTER_VERTICAL)
                textSize = 14f
                maxLines = 1
                isAllCaps = true
                ellipsize = TextUtils.TruncateAt.END
                typeface = Typeface.SANS_SERIF
                textColorResource = R.color.offerTitleTextColor
                backgroundColorResource = R.color.offerTitleBg
                setPadding(dip(12), 0, dip(16), 0)
            }.lparams(matchParent, dip(36))
        }
    }
}