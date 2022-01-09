package com.example.homemade.feature.postDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.ui.adapters.CommentAdapter
import com.example.homemade.ui.models.CommentModel
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {
    private lateinit var commentAdapter: CommentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        initAdapter()
    }

    private fun initAdapter() {
        val comments = arrayListOf<CommentModel>()
        comments.add(CommentModel(1, R.drawable.user_two, getString(R.string.comment)))
        comments.add(CommentModel(2, R.drawable.user, getString(R.string.comment)))
        comments.add(CommentModel(3, R.drawable.user_three, getString(R.string.comment)))
        comments.add(CommentModel(4, R.drawable.user_one, getString(R.string.comment)))
        commentAdapter = CommentAdapter(this, comments)
        rv_comments.adapter = commentAdapter
    }
}
