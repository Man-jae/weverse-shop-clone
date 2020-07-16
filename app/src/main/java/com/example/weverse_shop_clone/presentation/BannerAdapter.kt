package com.example.weverse_shop_clone.presentation

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weverse_shop_clone.data.model.BannerModel
import com.example.weverse_shop_clone.util.MeasuredViewPager


class BannerAdapter(
    fm: FragmentManager,
    private var items: List<BannerModel>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var currentPosition = -1

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Fragment {
        return BannerFragment(items[position])
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