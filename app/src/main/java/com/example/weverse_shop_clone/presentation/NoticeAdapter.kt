package com.example.weverse_shop_clone.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weverse_shop_clone.R
import com.example.weverse_shop_clone.data.model.NoticeModel
import kotlinx.android.synthetic.main.item_notice.view.*
import java.util.*

class NoticeAdapter(
    private val context: Context,
    private var items: ArrayList<NoticeModel>
) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_notice, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            textNoticeTitle.text = item.title
            textNoticeDate.text = item.date
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_click, context.getString(R.string.shop_notice_title)),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun setItems(items: ArrayList<NoticeModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textNoticeTitle: TextView = view.text_notice_title
        var textNoticeDate: TextView = view.text_notice_date
    }

}
