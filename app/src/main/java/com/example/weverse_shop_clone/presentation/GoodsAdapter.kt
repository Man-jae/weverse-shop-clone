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
import com.example.weverse_shop_clone.data.model.ShopModel
import kotlinx.android.synthetic.main.item_goods.view.*
import java.text.DecimalFormat
import java.util.*

class GoodsAdapter(
    private val context: Context,
    private val items: ArrayList<ShopModel.ShopSaleModel>
) : RecyclerView.Adapter<GoodsAdapter.ViewHolder>() {
    var commaFormat: DecimalFormat = DecimalFormat("###,###")

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_goods, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            textSoleSale.visibility = View.GONE
            textPreOrder.visibility = View.GONE

            Glide.with(context)
                .load(item.imageUrl)
                .thumbnail(0.1f)
                .into(viewGoods)

            textSoleSale.visibility = if (item.isMonopoly) View.VISIBLE else View.GONE
            textPreOrder.visibility = if (item.isPreOrder) View.VISIBLE else View.GONE
            textGoodsTitle.text = item.name
            textGoodsPrice.text = context.getString(
                R.string.goods_won,
                commaFormat.format(
                    item.salePrice?.toInt()
                        ?: item.originalPrice.toInt()
                )
            )
            itemView.setOnClickListener {
                Toast.makeText(context, context.getString(R.string.toast_click_goods), Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var viewGoods: ImageView = view.view_goods
        var textSoleSale: TextView = view.text_sole_sale
        var textPreOrder: TextView = view.text_pre_order
        var textGoodsTitle: TextView = view.text_goods_title
        var textGoodsPrice: TextView = view.text_goods_price
    }

}
