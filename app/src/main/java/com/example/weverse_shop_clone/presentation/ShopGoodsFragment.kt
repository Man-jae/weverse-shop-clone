package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weverse_shop_clone.R
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopGoodsFragment(private val position: Int) : BaseFragment() {
    private var goodsAdapter: GoodsAdapter? = null
    private var goodsList = arrayListOf<String>()

    override fun getLayoutResId(): Int = R.layout.fragment_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (position) {
            else -> {
                goodsList.add("")
                goodsList.add("")
            }
        }

        goodsAdapter = GoodsAdapter(activity as MainActivity, goodsList)
        recycler_goods.layoutManager = GridLayoutManager(context, 2)
        recycler_goods.adapter = goodsAdapter
        recycler_goods.isNestedScrollingEnabled = false
    }
}