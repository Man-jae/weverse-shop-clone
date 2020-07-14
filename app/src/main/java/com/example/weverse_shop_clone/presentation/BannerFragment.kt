package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import com.example.weverse_shop_clone.R

class BannerFragment(private val position: Int) : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_banner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (position) {
            else -> {
            }
        }
    }
}