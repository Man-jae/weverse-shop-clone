package com.example.weverse_shop_clone.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weverse_shop_clone.R
import kotlinx.android.synthetic.main.item_bottom_sheet_shop.view.*
import java.util.*

class BottomSheetShopAdapter(
    private val context: Context,
    private val items: ArrayList<String>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BottomSheetShopAdapter.ViewHolder>() {
    private var checked: RadioButton? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_bottom_sheet_shop, parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            textShop.text = item
            itemView.setOnClickListener {
                checked?.isChecked = false
                radioShop.isChecked = true
                checked = radioShop
                onItemClickListener.onItemClick(it, position)
            }
        }
    }

    class ViewHolder(
        view: View, var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(view) {
        var viewShop: ImageView = view.view_shop
        var textShop: TextView = view.text_shop
        var radioShop: RadioButton = view.radio_shop
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}
