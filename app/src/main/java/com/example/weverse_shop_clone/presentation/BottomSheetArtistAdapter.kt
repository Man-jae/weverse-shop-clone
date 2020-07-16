package com.example.weverse_shop_clone.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.ArtistModel
import kotlinx.android.synthetic.main.item_bottom_sheet_artist.view.*
import java.util.*

class BottomSheetArtistAdapter(
    private val context: Context,
    private var artist: Int,
    private var shop: String,
    private val items: ArrayList<ArtistModel>,
    private val onArtistShopListener: OnArtistShopListener
) : RecyclerView.Adapter<BottomSheetArtistAdapter.ViewHolder>(), BottomSheetShopAdapter.OnItemClickListener {
    private var shopAdapter: BottomSheetShopAdapter? = null
    private var shopList = arrayListOf<String>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_bottom_sheet_artist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            recyclerShop.visibility = View.GONE
            viewArrow.setBackgroundResource(R.drawable.ic_arrow_down)

            Glide.with(context)
                .load(item.imageUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .thumbnail(0.1f)
                .into(viewArtist)

            textArtist.text = item.name

            shopList = item.shops
            shopAdapter = BottomSheetShopAdapter(context, shopList, this@BottomSheetArtistAdapter)
            recyclerShop.apply {
                adapter = shopAdapter
            }

            itemView.setOnClickListener {
                artist = item.id
                if (recyclerShop.isShown) {
                    recyclerShop.visibility = View.GONE
                    viewArrow.setBackgroundResource(R.drawable.ic_arrow_down)
                } else {
                    recyclerShop.visibility = View.VISIBLE
                    viewArrow.setBackgroundResource(R.drawable.ic_arrow_up)
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var viewArtist: ImageView = view.view_artist
        var viewArrow: View = view.view_arrow
        var textArtist: TextView = view.text_artist_name
        var recyclerShop: RecyclerView = view.recycler_shop
    }

    interface OnArtistShopListener {
        fun select(view: View, artist: Int, shop: String)
    }

    override fun onItemClick(view: View, position: Int) {
        onArtistShopListener.select(view, artist, shopList[position])
    }
}
