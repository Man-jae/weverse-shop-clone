package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.weverse_shop_clone.R
import kotlinx.android.synthetic.main.fragment_main_shop.*

class MainShopFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_main_shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button_company_info.setOnClickListener {
            if (layout_company_info.isVisible) {
                layout_company_info.visibility = View.GONE
                view_arrow.isChecked = false
            } else {
                layout_company_info.visibility = View.VISIBLE
                view_arrow.isChecked = true
            }
        }

        button_back_to_top.setOnClickListener {
            scroll_view.smoothScrollTo(0, 0)
        }
    }
}