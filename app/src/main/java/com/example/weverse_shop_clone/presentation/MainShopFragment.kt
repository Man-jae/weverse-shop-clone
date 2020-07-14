package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.weverse_shop_clone.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main_shop.*

class MainShopFragment : BaseFragment() {
    private var recentGoodsAdapter: RecentGoodsAdapter? = null
    private var noticeAdapter: NoticeAdapter? = null
    private var recentGoodsList = arrayListOf<String>()
    private var noticeList = arrayListOf<String>()
    private var currentY = 0

    override fun getLayoutResId(): Int = R.layout.fragment_main_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button_company_info.setOnClickListener(onClickListener)
        button_back_to_top.setOnClickListener(onClickListener)

        scroll_view.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            currentY = scrollY
        }

        initGoods()
        initRecentGoods()
        initNotice()
    }

    private fun initGoods() {
        val adapter: PagerAdapter =
            ShopGoodsAdapter((activity as MainActivity).supportFragmentManager, layout_tab_shop.tabCount)
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
            R.id.button_company_info -> {
                if (layout_company_info.isVisible) {
                    layout_company_info.visibility = View.GONE
                    view_arrow.isChecked = false
                } else {
                    layout_company_info.visibility = View.VISIBLE
                    view_arrow.isChecked = true
                }
            }

            R.id.button_back_to_top -> {
                scroll_view.smoothScrollTo(0, 0)
            }
        }
    }
}