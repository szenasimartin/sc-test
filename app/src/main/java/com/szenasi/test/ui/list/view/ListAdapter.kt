package com.szenasi.test.ui.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szenasi.test.R
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.detail.view.DetailIntent
import com.szenasi.test.utils.load
import kotlinx.android.synthetic.main.item_list.view.*

class SearchResultAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val items = ArrayList<ItemWithDetails>()

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
        holder.posterImage.load(items[position].posterPath)
        holder.itemBudget.text = "budget: %.2f M USD".format(items[position].budget/1000000)
    }

    fun addItem(item: ItemWithDetails) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemNameView = view.itemName
    val posterImage = view.posterImage
    val itemBudget = view.itemBudget
}