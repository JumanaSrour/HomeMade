package com.example.homemade.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.homemade.R
import com.example.homemade.ui.models.Grocery
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class GroceryAdapter(val context: Context, val data: ArrayList<Grocery>) :
    RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {
    private var listener: CategoryAdapter.SetClickListener? = null

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var groceryItem: RadioButton = itemview.rb_potato
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.groceryItem.text = data[position].grocery_name
    }

    override fun getItemCount() = data.size
}
