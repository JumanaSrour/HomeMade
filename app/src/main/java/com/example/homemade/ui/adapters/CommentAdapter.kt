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
import com.example.homemade.ui.models.CommentModel
import kotlinx.android.synthetic.main.comment_card_item.view.*

class CommentAdapter(val context: Context, val data: ArrayList<CommentModel>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentImage: ImageView = itemView.iv_user_pic_comment
        var commentDescription: TextView = itemView.tv_comment_text
        var commentTime: TextView = itemView.tv_comment_timing
        var commentUser: TextView = itemView.tv_comment_user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentDescription.text = data[position].comment_text
        Glide.with(context).load(data[position].comment_image).circleCrop().into(holder.commentImage)
    }

    override fun getItemCount() = data.size
}
