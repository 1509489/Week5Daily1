package com.pixelart.week5daily1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixelart.week5daily1.R

class RecyclerViewAdapter(val users: List<Any>, val listener: RecyclerViewAdapter.OnItemClickedListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
   lateinit var context:Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        context = viewGroup.context
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClicked(position)
        }
    }

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }
}