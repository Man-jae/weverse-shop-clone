package com.example.weverse_shop_clone.presentation

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.local.AppDatabase
import com.example.weverse_shop_clone.util.MeasuredViewPager


class ShopGoodsAdapter(
    fm: FragmentManager,
    var db: AppDatabase,
    var artistId: Int,
    var shopInfo: ArrayList<ShopModel>,
    var tabCount: Int
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var currentPosition = -1

    override fun getCount(): Int = tabCount
    override fun getItem(position: Int): Fragment {
        return ShopGoodsFragment(db, artistId, shopInfo[position])
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (position != currentPosition) {
            val fragment = `object` as Fragment?
            val pager: MeasuredViewPager = container as MeasuredViewPager
            if (fragment != null && fragment.view != null) {
                currentPosition = position
                pager.measureCurrentView(fragment.view)
            }
        }
    }
}