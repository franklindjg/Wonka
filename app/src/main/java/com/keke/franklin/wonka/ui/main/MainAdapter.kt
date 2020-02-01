package com.keke.franklin.wonka.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.data.network.model.Result
import kotlinx.android.synthetic.main.merge_item_row.view.*

class MainAdapter(private var items: MutableList<Result>, private val listener: (Int) -> Unit):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.merge_item_row, parent, false)
        return MainViewHolder(root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.root.name.text = StringBuilder().append(item.firstName).append(" ").append(item.lastName)
        holder.root.gender.text = StringBuilder().append(holder.root.context.getString(R.string.gender)).append(" ").append(item.gender)
        holder.root.setOnClickListener { listener(item.id!!) }
    }

    class MainViewHolder(val root: View): RecyclerView.ViewHolder(root)

    fun add(list: List<Result>) {
        items.addAll(list)
    }
}