package com.applexis.napoleonittask.offers

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.app.SearchManager
import android.content.Intent
import android.graphics.Canvas
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.applexis.napoleonittask.data.banner.BannerInfo
import com.applexis.napoleonittask.data.offer.OfferInfo
import com.applexis.napoleonittask.offers.adapters.BannerPageAdapter
import com.applexis.napoleonittask.offers.adapters.OfferRecyclerAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.applexis.napoleonittask.R
import android.view.MotionEvent
import org.jetbrains.anko.*
import android.graphics.RectF
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.ItemTouchHelper.*
import com.applexis.napoleonittask.utils.SwipeController
import androidx.recyclerview.widget.LinearLayoutManager


class OfferListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val offerListViewModelFactory: OfferListViewModelFactory by instance()
    private lateinit var offerRecyclerAdapter: OfferRecyclerAdapter
    private lateinit var ui: OfferListActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = OfferListActivityUI()
        ui.setContentView(this)

        setSupportActionBar(ui.toolbar)

        val pagerAdapter = BannerPageAdapter()
        ui.viewPager.adapter = pagerAdapter

        setupRecyclerView()

        offerRecyclerAdapter = OfferRecyclerAdapter(this)
        ui.recyclerView.adapter = offerRecyclerAdapter

        val viewModel = ViewModelProviders.of(this, offerListViewModelFactory).get(OfferListViewModel::class.java)
        viewModel.bannerList.observe(this, Observer<List<BannerInfo>> { bannerList ->
            pagerAdapter.data = bannerList
        })
        viewModel.offerList.observe(this, Observer<List<OfferInfo>> { offerList ->
            offerRecyclerAdapter.data = offerList
        })
        viewModel.isOfferListLoading.observe(this, Observer<Boolean> { isOfferListLoading ->
            ui.swipeRefreshLayout.isRefreshing = isOfferListLoading
        })
        viewModel.isBannerListLoading.observe(this, Observer<Boolean> { isBannerListLoading ->
            if (isBannerListLoading) {
                ui.progressBar.visibility = View.VISIBLE
                ui.viewPager.visibility = View.GONE
            } else {
                ui.progressBar.visibility = View.GONE
                ui.viewPager.visibility = View.VISIBLE
            }
        })
        viewModel.loadData()
        ui.swipeRefreshLayout.setOnRefreshListener { viewModel.refreshOfferList() }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            ui.searchView.setQuery(query.toString(), false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.offer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_info -> {
                alert(
                    getString(R.string.info_message, "Лебедев Владислав", "Kodein, Anko, Ktor, SQLDelight, Lifecycle"),
                    getString(R.string.info_title)
                ) {
                    okButton { dialog -> dialog.dismiss() }
                }.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setupRecyclerView() {
        val swipeController = SwipeController(this, dip(80).toFloat())

        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(ui.recyclerView)

        ui.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }
}
