package com.applexis.napoleonittask.offers.adapters

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applexis.napoleonittask.R
import com.applexis.napoleonittask.data.offer.OfferInfo
import com.applexis.napoleonittask.utils.load
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textColor

class OfferRecyclerAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<OfferInfo>()
        set(value) {
            field = value
            promotionCount = value.count { it.type == OfferInfo.promotion }
            notifyDataSetChanged()
        }
    private var promotionCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 0) {
            val ui = OfferTitleItemUI()
            val view = ui.createView(AnkoContext.Companion.create(parent.context, parent, false))
            OfferTitleViewHolder(view, ui)
        } else {
            val ui = OfferItemUI()
            val view = ui.createView(AnkoContext.Companion.create(parent.context, parent, false))
            OfferViewHolder(view, ui)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OfferViewHolder) {
            val pos = when {
                position < promotionCount + 1 -> position - 1
                position > promotionCount + 1 -> position - 2
                else -> 0
            }
            val offerInfo = data[pos]
            holder.image.load(Uri.parse(offerInfo.image), R.drawable.ic_placeholder)
            holder.title.text = offerInfo.name
            holder.description.text = offerInfo.desc ?: mContext.getString(R.string.offer_empty_desc)
            if (offerInfo.discount != null && offerInfo.discount != 0f && offerInfo.price != null) {
                holder.discount.visibility = View.VISIBLE
                holder.discount.text = "-${(offerInfo.discount * 100).toInt()}%"
                holder.discountPrice.textColor = mContext.resources.getColor(R.color.offerDiscount)
                holder.price.text = mContext.getString(R.string.offer_price_rub, offerInfo.price)
                holder.discountPrice.text =
                    mContext.getString(R.string.offer_price_rub, (offerInfo.price * (1 - offerInfo.discount)).toInt())
            } else {
                holder.discount.visibility = View.GONE
                holder.discountPrice.textColor = mContext.resources.getColor(R.color.offerSecondaryColor)
                holder.price.text = ""
                holder.discountPrice.text =
                    if (offerInfo.price != null) mContext.getString(R.string.offer_price_rub, offerInfo.price)
                    else mContext.getString(R.string.offer_price_not_available)
            }
        } else if (holder is OfferTitleViewHolder) {
            holder.header.text = mContext.getString(
                if (position == 0) R.string.promotion_header
                else R.string.discount_header
            )
        }
    }

    override fun getItemCount() = data.size + 2

    override fun getItemViewType(position: Int): Int =
        when {
            position == 0 -> 0
            position < promotionCount + 1 -> 1
            position == promotionCount + 1 -> 0
            else -> 1
        }

    class OfferViewHolder(val root: View, ui: OfferItemUI) : RecyclerView.ViewHolder(root) {
        val image = ui.image
        val title = ui.title
        val description = ui.description
        val price = ui.price
        val discount = ui.discount
        val discountPrice = ui.discountPrice
        //val removeButton = ui.removeButton
    }

    class OfferTitleViewHolder(val root: View, ui: OfferTitleItemUI) : RecyclerView.ViewHolder(root) {
        val header = ui.header
    }

}
