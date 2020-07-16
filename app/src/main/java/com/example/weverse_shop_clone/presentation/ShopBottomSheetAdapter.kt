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

class ShopBottomSheetAdapter(
    private val context: Context,
    private val items: ArrayList<ArtistModel>
) : RecyclerView.Adapter<ShopBottomSheetAdapter.ViewHolder>() {
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
            Glide.with(context)
                .load(item.imageUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .thumbnail(0.1f)
                .into(viewArtist)

            textArtist.text = item.name
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var viewArtist: ImageView = view.view_artist
        var textArtist: TextView = view.text_artist_name
    }

}
