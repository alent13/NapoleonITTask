package com.applexis.napoleonittask.offers.adapters

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.applexis.napoleonittask.R
import com.applexis.napoleonittask.data.banner.BannerInfo
import com.applexis.napoleonittask.utils.load
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent

class BannerPageAdapter : PagerAdapter() {

    var data: List<BannerInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun isViewFromObject(view: View, other: Any): Boolean = view == other

    override fun getCount(): Int = data.size

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ui = BannerItemUI()
        val view = ui.createView(AnkoContext.create(container.context, container))

        data[position].image?.apply { ui.image.load(Uri.parse(this), R.drawable.ic_placeholder) }
        ui.title.text = data[position].title ?: ""
        ui.description.text = data[position].desc ?: ""

        container.addView(view)
        return view
    }

}