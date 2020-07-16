package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.mapper.ArtistMapper
import com.example.weverse_shop_clone.data.mapper.BannerMapper
import com.example.weverse_shop_clone.data.mapper.NoticeMapper
import com.example.weverse_shop_clone.data.mapper.ShopMapper
import com.example.weverse_shop_clone.data.model.ArtistModel
import com.example.weverse_shop_clone.data.model.BannerModel
import com.example.weverse_shop_clone.data.model.NoticeModel
import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.remote.ServerManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main_shop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainShopFragment : BaseFragment() {
    private var recentGoodsAdapter: RecentGoodsAdapter? = null
    private var noticeAdapter: NoticeAdapter? = null
    private var bannerList = arrayListOf<BannerModel>()
    private var shopList = arrayListOf<ShopModel>()
    private var recentGoodsList = arrayListOf<String>()
    private var noticeList = arrayListOf<NoticeModel>()
    private var currentY = 0

    override fun getLayoutResId(): Int = R.layout.fragment_main_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        layout_artist.setOnClickListener(onClickListener)
        layout_artist_shop_title.setOnClickListener(onClickListener)
        button_company_info.setOnClickListener(onClickListener)
        button_back_to_top.setOnClickListener(onClickListener)
        text_privacy_policy.setOnClickListener(onClickListener)
        text_terms_of_use.setOnClickListener(onClickListener)
        text_paid_service_terms.setOnClickListener(onClickListener)

        swipe_layout.setOnRefreshListener {
            (activity as MainActivity).apply {
                getInfo(artistId, shop)
            }
        }

        scroll_view.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            currentY = scrollY
            layout_artist_shop_title.visibility = if (currentY > layout_artist.top) View.VISIBLE else View.GONE
        }

        initRecentGoods()
        initNotice()

        (activity as MainActivity).apply {
            getInfo(artistId, shop)
        }
    }

    private fun initBanner() {
        viewpager_banner.adapter = BannerAdapter((activity as MainActivity).supportFragmentManager, bannerList)
    }

    private fun initGoods() {
        layout_tab_shop.removeAllTabs()
        shopList.forEach { shop ->
            layout_tab_shop.addTab(layout_tab_shop.newTab().setText(shop.category.name))
        }

        val adapter: PagerAdapter =
            ShopGoodsAdapter((activity as MainActivity).supportFragmentManager, shopList, layout_tab_shop.tabCount)
        viewpager_goods.adapter = adapter
        viewpager_goods.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layout_tab_shop))
        layout_tab_shop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager_goods.currentItem = tab.position
                if (currentY > layout_goods.top) {
                    scrollToView(layout_goods, scroll_view, 0)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initRecentGoods() {
        recentGoodsAdapter = RecentGoodsAdapter((activity as MainActivity), recentGoodsList)
        recycler_goods_recent.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentGoodsAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun initNotice() {
        noticeAdapter = NoticeAdapter((activity as MainActivity), noticeList)
        recycler_notice.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
            isNestedScrollingEnabled = false
        }
    }

    fun getInfo(artistId: Int, shop: String) {
        swipe_layout.isRefreshing = true
        bannerList.clear()
        shopList.clear()
        noticeList.clear()

        scroll_view.smoothScrollTo(0, 0)

        CoroutineScope(Dispatchers.IO).launch {
            val response = ServerManager.getInfo(artistId)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    bannerList = body.banners.map(BannerMapper::mapToData) as ArrayList<BannerModel>
                    shopList = body.shops.map(ShopMapper::mapToData) as ArrayList<ShopModel>
                    noticeList = body.notices.map(NoticeMapper::mapToData) as ArrayList<NoticeModel>

                    withContext(Dispatchers.Main) {
                        swipe_layout.isRefreshing = false
                        val artistName = body.artists[artistId - 1].name
                        text_artist_name.text = artistName
                        text_artist_shop.text = shop
                        text_artist_shop_title.text = getString(R.string.artist_shop_title, artistName, shop)

                        initBanner()
                        initGoods()
                        noticeAdapter?.setItems(noticeList)
                        (activity as MainActivity).apply {
                            artistShopList.clear()
                            artistShopList = body.artists.map(ArtistMapper::mapToData) as ArrayList<ArtistModel>
                            initBottomSheet()
                        }
                    }
                }
            }
        }
    }

    private fun scrollToView(view: View, scrollView: NestedScrollView, count: Int) {
        var count = count
        if (view != scrollView) {
            count += view.top
            scrollToView(view.parent as View, scrollView, count)
        } else {
            val finalCount = count
            Handler().postDelayed({
                scrollView.smoothScrollTo(0, finalCount)
            }, 200)
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.layout_artist,
            R.id.layout_artist_shop_title -> {
                (activity as MainActivity).artistShopBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }

            R.id.button_company_info -> {
                if (layout_company_info.isVisible) {
                    layout_company_info.visibility = View.GONE
                    view_arrow.isChecked = false
                } else {
                    layout_company_info.visibility = View.VISIBLE
                    view_arrow.isChecked = true
                }
            }

            R.id.text_privacy_policy -> {
                Toast.makeText(
                    context,
                    getString(R.string.toast_click, getString(R.string.company_privacy_policy)),
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.text_terms_of_use -> {
                Toast.makeText(
                    context,
                    getString(R.string.toast_click, getString(R.string.company_terms_of_use)),
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.text_paid_service_terms -> {
                Toast.makeText(
                    context,
                    getString(R.string.toast_click, getString(R.string.company_paid_service_terms)),
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.button_back_to_top -> {
                scroll_view.smoothScrollTo(0, 0)
            }
        }
    }
}