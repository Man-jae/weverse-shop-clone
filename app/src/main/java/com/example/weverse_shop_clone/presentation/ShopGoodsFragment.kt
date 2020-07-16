package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.local.AppDatabase
import com.example.weverse_shop_clone.util.SpaceDecoration
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopGoodsFragment(
    private val db: AppDatabase,
    private val artistId: Int,
    private val shopInfo: ShopModel
) : BaseFragment() {
    private var goodsAdapter: GoodsAdapter? = null

    override fun getLayoutResId(): Int = R.layout.fragment_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goodsAdapter = GoodsAdapter(activity as MainActivity, db, artistId, shopInfo.sales)
        recycler_goods.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = goodsAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(SpaceDecoration(2))
        }

        button_goods_more.setOnClickListener {
            Toast.makeText(
                context,
                getString(R.string.toast_click, getString(R.string.shop_goods_more_button)),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}