package com.szenasi.test.ui.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szenasi.test.R
import com.szenasi.test.data.model.Item
import com.szenasi.test.ui.detail.view.DetailIntent
import kotlinx.android.synthetic.main.item_list.view.*

class SearchResultAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val items = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemNameView.text = items[position].name
        holder.itemView.setOnClickListener {
            context.startActivity(context.DetailIntent(items[position]))
        }
    }

    fun addItem(item: List<Item>) {
        items.addAll(item)
        notifyDataSetChanged()
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemNameView = view.itemName
}