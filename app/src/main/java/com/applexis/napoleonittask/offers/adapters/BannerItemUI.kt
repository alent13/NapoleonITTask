package com.applexis.napoleonittask.offers.adapters

import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.core.view.marginBottom
import com.applexis.napoleonittask.R
import com.applexis.napoleonittask.utils.roundImageView
import com.makeramen.roundedimageview.RoundedImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class BannerItemUI : AnkoComponent<ViewGroup> {

    lateinit var image: RoundedImageView
    lateinit var title: TextView
    lateinit var description: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        constraintLayout {
            lparams {
                width = matchParent
                height = matchParent
            }

            image = roundImageView {
                lparams(matchParent, matchParent)
                id = R.id.bannerImage
                cornerRadius = dip(16).toFloat()
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams {
                width = dip(0)
                height = dip(0)
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
            }
            view {
                id = R.id.bannerBg
                backgroundResource = R.drawable.banner_bottom_bg
            }.lparams {
                height = dip(52)
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                bottomToBottom = R.id.bannerImage
            }
            title = textView {
                id = R.id.bannerTitleText
                this.gravity = Gravity.START
                textSize = 16f
                maxLines = 1
                ellipsize = TextUtils.TruncateAt.END
                setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                textColorResource = android.R.color.black
            }.lparams {
                width = 0
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                bottomToTop = R.id.bannerDescriptionText
                topToTop = R.id.bannerBg
                setMargins(dip(12), dip(8), dip(16), 0)
            }
            description = textView {
                id = R.id.bannerDescriptionText
                this.gravity = Gravity.START
                textSize = 16f
                maxLines = 1
                ellipsize = TextUtils.TruncateAt.END
                typeface = Typeface.SANS_SERIF
                textColorResource = R.color.secondaryTextColor
            }.lparams {
                width = 0
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                topToBottom = R.id.bannerTitleText
                bottomToBottom = R.id.bannerBg
                setMargins(dip(12), 0, dip(16), dip(8))
            }
        }
    }
}
