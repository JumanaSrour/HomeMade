package com.example.homemade.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homemade.R
import com.example.homemade.ui.models.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(val context: Context, val data: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var listener: SetClickListener? = null

    inner class ViewHolder internal constructor(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        var postImageView: ImageView = itemview.iv_recipe1
        var postDesc: TextView = itemview.tv_recipe1
        var postCard: CardView = itemview.cv_post
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postDesc.text = data[position].post_desc
        Glide.with(context).load(data[position].post_image).into(holder.postImageView)
        holder.postCard.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: clicked")
            listener?.onItemClickListener(position, data[position])
        }
    }

    public fun setListener(listener: SetClickListener) {
        this.listener = listener
    }

    private fun getItem(position: Int): Post {
        return data.get(position)
    }

    override fun getItemCount() = data.size

    interface SetClickListener {
        fun onItemClickListener(position: Int, post: Post)
    }
}
