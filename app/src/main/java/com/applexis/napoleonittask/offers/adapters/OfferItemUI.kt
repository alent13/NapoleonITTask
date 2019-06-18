package com.applexis.napoleonittask.offers.adapters

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.applexis.napoleonittask.R
import com.applexis.napoleonittask.utils.roundImageView
import com.makeramen.roundedimageview.RoundedImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onTouch

class OfferItemUI : AnkoComponent<ViewGroup> {

    lateinit var image: RoundedImageView
    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var price: TextView
    lateinit var discount: TextView
    lateinit var discountPrice: TextView
    lateinit var removeButton: View

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, dip(112))
            constraintLayout {
                lparams(matchParent, matchParent)
                backgroundColorResource = android.R.color.white

                image = roundImageView {
                    id = R.id.image
                    cornerRadius = dip(8).toFloat()
                }.lparams(dip(112), dip(80)) {
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                    startToStart = PARENT_ID
                    setMargins(dip(12), 0, 0, 0)
                }
                title = textView {
                    id = R.id.offerTitle
                    this.gravity = Gravity.START
                    textSize = 16f
                    maxLines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                    textColorResource = android.R.color.black
                }.lparams {
                    width = 0
                    startToEnd = R.id.image
                    endToEnd = PARENT_ID
                    topToTop = PARENT_ID
                    setMargins(dip(12), dip(12), dip(16), 0)
                }
                description = textView {
                    id = R.id.offerDescription
                    this.gravity = Gravity.START
                    textSize = 16f
                    maxLines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                    textColorResource = R.color.offerSecondaryColor
                }.lparams {
                    width = 0
                    startToEnd = R.id.image
                    endToEnd = PARENT_ID
                    topToBottom = R.id.offerTitle
                    setMargins(dip(12), 0, dip(16), 0)
                }
                discountPrice = textView {
                    id = R.id.offerDiscountPrice
                    textSize = 16f
                    maxLines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                    textColorResource = android.R.color.black
                }.lparams {
                    endToEnd = PARENT_ID
                    bottomToBottom = R.id.image
                    setMargins(0, 0, dip(12), dip(2))
                }
                price = textView {
                    id = R.id.offerPrice
                    textSize = 15f
                    maxLines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                    textColorResource = R.color.offerSecondaryColor
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }.lparams {
                    endToEnd = PARENT_ID
                    bottomToTop = R.id.offerDiscountPrice
                    setMargins(0, 0, dip(12), 0)
                }
                discount = textView {
                    id = R.id.offerDiscount
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                    textSize = 16f
                    maxLines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                    textColorResource = android.R.color.white
                    backgroundResource = R.drawable.discount_bg
                    horizontalPadding = dip(8)
                }.lparams {
                    height = dip(24)
                    width = wrapContent
                    startToEnd = R.id.offerBasket
                    bottomToBottom = R.id.image
                    setPadding(dip(8), 0, dip(8), 0)
                    setMargins(dip(16), 0, dip(12), 0)
                }
                imageView {
                    id = R.id.offerBasket
                    setImageResource(R.drawable.ic_basket)
                }.lparams {
                    width = dip(24)
                    height = dip(24)
                    startToEnd = R.id.image
                    bottomToBottom = R.id.image
                    setMargins(dip(12), 0, 0, 0)
                }
            }
            view {
                backgroundColorResource = R.color.offerDividerColor
            }.lparams(matchParent, dip(1)) {
                alignParentTop()
            }
        }
    }
}