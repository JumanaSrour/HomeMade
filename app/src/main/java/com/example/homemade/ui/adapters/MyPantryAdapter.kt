package com.example.homemade.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homemade.R
import com.example.homemade.ui.models.Pantry
import kotlinx.android.synthetic.main.pantry_card_item.view.*

class MyPantryAdapter(val context: Context, val data: ArrayList<Pantry>): RecyclerView.Adapter<MyPantryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pantryName: TextView = itemView.tv_pantry_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pantry_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pantryName.text = data[position].pantry_name
    }

    override fun getItemCount()= data.size
}