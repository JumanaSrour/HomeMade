package com.example.homemade.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homemade.R
import com.example.homemade.ui.models.Category
import kotlinx.android.synthetic.main.category_card_item.view.*

class CategoryAdapter(val context: Context, val data: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var listener: SetClickListener? = null

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var categoryImage: ImageView = itemview.iv_diary
        var categoryName: TextView = itemview.info_text
        var categoryCard: CardView = itemview.cv_diary
        var categoryAddButton: Button = itemview.btn_add_ingredient
        var categoryIngredientsNumber: TextView = itemview.info_number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = data[position].category_name
        holder.categoryCard.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: clicked")
            listener?.onItemClickListener(position, data[position])
        }
        holder.categoryAddButton.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: button clicked")
            listener?.onButtonClickListener(position, holder.categoryAddButton)
        }
        Glide.with(context).load(data[position].category_image).circleCrop().into(holder.categoryImage)
    }

    public fun setListener(listener: SetClickListener) {
        this.listener = listener
    }

    override fun getItemCount() = data.size

    interface SetClickListener {
        fun onItemClickListener(position: Int, category: Category)
        fun onButtonClickListener(position: Int, button: Button)
    }
}
