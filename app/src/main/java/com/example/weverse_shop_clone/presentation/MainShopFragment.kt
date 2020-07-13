package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.viewpager.widget.PagerAdapter
import com.example.weverse_shop_clone.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main_shop.*

class MainShopFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_main_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button_company_info.setOnClickListener(onClickListener)
        button_back_to_top.setOnClickListener(onClickListener)

        initGoods()
    }

    private fun initGoods() {
        val adapter: PagerAdapter =
            ShopGoodsAdapter((activity as MainActivity).supportFragmentManager, layout_tab_shop.tabCount)
        viewpager_goods.adapter = adapter
        viewpager_goods.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layout_tab_shop))
        layout_tab_shop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager_goods.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
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