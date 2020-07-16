package com.example.weverse_shop_clone.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.local.AppDatabase
import com.example.weverse_shop_clone.data.source.local.ShopDataBase
import kotlinx.android.synthetic.main.item_goods.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.util.*

class GoodsAdapter(
    private val context: Context,
    private val db: AppDatabase,
    private val artistId: Int,
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
            textFanOnly.visibility = View.GONE
            layoutSoldOut.visibility = View.GONE

            Glide.with(context)
                .load(item.imageUrl)
                .thumbnail(0.1f)
                .into(viewGoods)

            textSoleSale.visibility = if (item.isMonopoly) View.VISIBLE else View.GONE
            textPreOrder.visibility = if (item.isPreOrder) View.VISIBLE else View.GONE
            textFanOnly.visibility = if (item.isFcOnly) View.VISIBLE else View.GONE
            layoutSoldOut.visibility = if (item.isSoldOut) View.VISIBLE else View.GONE
            textGoodsTitle.text = item.name
            textGoodsPrice.text = context.getString(
                R.string.goods_won,
                commaFormat.format(
                    item.salePrice?.toInt()
                        ?: item.originalPrice.toInt()
                )
            )
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_click, context.getString(R.string.shop_goods_title)),
                    Toast.LENGTH_SHORT
                ).show()

                CoroutineScope(Dispatchers.IO).launch {
                    db.shopDao().create(
                        ShopDataBase(
                            id = item.id,
                            artistId = artistId,
                            title = item.name,
                            imageUrl = item.imageUrl,
                            createAt = Date()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        val navFragment =
                            (context as MainActivity).supportFragmentManager.findFragmentById(R.id.fragment_main)
                        val fragment = navFragment?.childFragmentManager?.fragments?.get(0) as MainShopFragment

                        fragment.getRecentGoods()
                    }
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var viewGoods: ImageView = view.view_goods
        var textSoleSale: TextView = view.text_sole_sale
        var textPreOrder: TextView = view.text_pre_order
        var textFanOnly: TextView = view.text_fan_only
        var textGoodsTitle: TextView = view.text_goods_title
        var textGoodsPrice: TextView = view.text_goods_price
        var layoutSoldOut: ConstraintLayout = view.layout_sold_out
    }

}
