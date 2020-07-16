package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.BannerModel
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment(private val banner: BannerModel) : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_banner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
            .load(banner.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
            .thumbnail(0.1f)
            .into(view_banner)

        text_banner_title.text = banner.title
        text_banner_sub_title.text = banner.subTitle
        layout_item.setOnClickListener {
            Toast.makeText(context, getString(R.string.toast_click_banner), Toast.LENGTH_SHORT).show()
        }
    }
}