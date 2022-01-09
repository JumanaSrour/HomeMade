package com.example.homemade.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homemade.R
import com.example.homemade.ui.models.Category
import kotlinx.android.synthetic.main.category_card_item.view.*

class CategoryAdapter(val context: Context, val data: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var categoryImage: ImageView = itemview.iv_diary
        var categoryName: TextView = itemview.info_text
        var categoryIngredientsNumber: TextView = itemview.info_number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = data[position].category_name
        Glide.with(context).load(data[position].category_image).circleCrop().into(holder.categoryImage)
    }

    override fun getItemCount() = data.size
}
