package com.applexis.napoleonittask.offers

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.searchView
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import com.applexis.napoleonittask.R
import com.applexis.napoleonittask.utils.reduceMarginsInTabs
import com.google.android.material.appbar.AppBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.viewPager


class OfferListActivityUI : AnkoComponent<OfferListActivity> {

    val tabList = listOf(R.string.tab_name_top_10, R.string.tab_name_shops, R.string.tab_name_products)

    lateinit var toolbar: Toolbar
    lateinit var searchView: SearchView
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var viewPager: ViewPager
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<OfferListActivity>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                lparams(matchParent, wrapContent)

                collapsingToolbarLayout {
                    verticalLayout {
                        toolbar = toolbar {
                            lparams(matchParent, wrapContent)

                            cardView {
                                lparams(matchParent, wrapContent)

                                searchView = searchView {
                                    setIconifiedByDefault(false)
                                    val searchManager =
                                        ctx.getSystemService(Context.SEARCH_SERVICE) as SearchManager
                                    setSearchableInfo(
                                        searchManager.getSearchableInfo(
                                            ComponentName(
                                                ctx,
                                                OfferListActivity::class.java
                                            )
                                        )
                                    )
                                    queryHint = ctx.getString(R.string.search_hint)
                                    /*onQueryTextListener {

                                }*/
                                }
                            }
                        }

                        frameLayout {
                            lparams(width = matchParent, height = dimenAttr(android.R.attr.actionBarSize))
                            tabLayout = themedTabLayout(R.style.OfferListTabLayout) {
                                lparams(width = matchParent, height = dip(31), gravity = Gravity.CENTER)

                                tabRippleColor = null
                                isTabIndicatorFullWidth = false
                                tabMode = TabLayout.MODE_AUTO
                                tabGravity = TabLayout.GRAVITY_CENTER
                                tabList.forEach { addTab(newTab().setText(it)) }
                                reduceMarginsInTabs(dip(9))
                            }
                        }
                    }
                }
            }

            nestedScrollView {
                isFillViewport = true
                verticalLayout {
                    verticalLayout {
                        frameLayout {
                            viewPager = viewPager {
                                clipToPadding = false
                                setPaddingRelative(dip(64), dip(16), dip(64), dip(16))
                                pageMargin = dip(16)
                            }.lparams(matchParent, matchParent)
                            progressBar =
                                progressBar { isIndeterminate = true }.lparams { gravity = Gravity.CENTER }
                        }.lparams(matchParent, dip(190))
                        swipeRefreshLayout = swipeRefreshLayout {
                            recyclerView = recyclerView {
                                lparams(matchParent, matchParent)

                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)
                                layoutAnimation =
                                    AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)
                            }
                        }.lparams(matchParent, matchParent)
                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }
}