package com.pravinkumarp.mvvmdemo.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pravinkumarp.mvvmdemo.R
import com.pravinkumarp.mvvmdemo.model.bean.Fruit

class MainActivityFruitListAdapter(private val fruits: ArrayList<Fruit>, private val fruitListItemClickListener: OnFruitListItemClickListener) : RecyclerView.Adapter<MainActivityFruitListAdapter.MainActivityFruitListItemHolder>() {

    inner class MainActivityFruitListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityFruitListItemHolder {
        return MainActivityFruitListItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main_fruit_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainActivityFruitListItemHolder, position: Int) {
        val fruit = fruits[holder.adapterPosition]
        holder.itemView.findViewById<TextView>(R.id.tvFruitName).text = fruit.name
        holder.itemView.findViewById<TextView>(R.id.tvFruitDescription).text = fruit.description

        holder.itemView.findViewById<View>(R.id.llFruitItem).setOnClickListener {
            fruitListItemClickListener.onFruitListItemClicked(fruit)
        }
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    interface OnFruitListItemClickListener {
        fun onFruitListItemClicked(fruit: Fruit)
    }
}