package com.example.weverse_shop_clone.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.ArtistModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_artist_shop.*

class MainActivity : AppCompatActivity(), ShopBottomSheetAdapter.OnItemClickListener {
    var artistShopBehavior: BottomSheetBehavior<*>? = null
    var artistShopAdapter: ShopBottomSheetAdapter? = null
    var artistShopList = arrayListOf<ArtistModel>()
    var artistId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_outside.setOnClickListener(onClickListener)

        bottom_navigation.setupWithNavController(findNavController(R.id.fragment_main))
    }

    override fun onBackPressed() {
        artistShopBehavior?.let {
            if (it.state != BottomSheetBehavior.STATE_HIDDEN) {
                it.setState(BottomSheetBehavior.STATE_COLLAPSED)
            } else {
                super.onBackPressed()
            }
        }
    }

    fun initBottomSheet() {
        artistShopBehavior = BottomSheetBehavior.from(bottom_sheet_artist_shop)
        artistShopAdapter = ShopBottomSheetAdapter(this, artistShopList, this)
        recycler_artist.adapter = artistShopAdapter
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.view_outside -> {
                artistShopBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val navFragment = supportFragmentManager.findFragmentById(R.id.fragment_main)
        val fragment = navFragment?.childFragmentManager?.fragments?.get(0) as MainShopFragment

        artistId = artistShopList[position].id
        artistShopBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        fragment.getInfo(artistId)
    }
}