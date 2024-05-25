package com.sksinha2410.ananotherreyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildAdapter(private val childList: List<ChildItem>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_child, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(childList[position])
    }

    override fun getItemCount(): Int = childList.size

    class ChildViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(childItem: ChildItem) {
            view.findViewById<TextView>(R.id.child_title).text = childItem.title
        }
    }
}