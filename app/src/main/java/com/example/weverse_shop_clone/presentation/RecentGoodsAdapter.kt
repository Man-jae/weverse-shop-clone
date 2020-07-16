package com.example.weverse_shop_clone.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.source.local.ShopDataBase
import kotlinx.android.synthetic.main.item_recent_goods.view.*
import java.util.*

class RecentGoodsAdapter(
    private val context: Context,
    private var items: ArrayList<ShopDataBase>
) : RecyclerView.Adapter<RecentGoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_recent_goods, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            Glide.with(context)
                .load(item.imageUrl)
                .thumbnail(0.1f)
                .into(viewGoods)

            textGoods.text = item.title

            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_click, context.getString(R.string.shop_goods_title)),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun setItems(items: ArrayList<ShopDataBase>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewGoods: ImageView = view.view_goods
        val textGoods: TextView = view.text_goods_title
    }
}
