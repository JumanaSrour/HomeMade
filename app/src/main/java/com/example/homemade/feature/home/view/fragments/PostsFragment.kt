package com.example.homemade.feature.home.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homemade.R
import com.example.homemade.feature.postDetails.PostDetailsActivity
import com.example.homemade.ui.adapters.PostAdapter
import com.example.homemade.ui.models.Post
import kotlinx.android.synthetic.main.fragment_posts.view.*

class PostsFragment : Fragment(R.layout.fragment_posts), PostAdapter.SetClickListener {
    private lateinit var postAdapter: PostAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val posts = arrayListOf<Post>()
        posts.add(Post(1, R.drawable.recipe_one, getString(R.string.recipe_one)))
        posts.add(Post(2, R.drawable.recipe_two, getString(R.string.recipe_two)))
        posts.add(Post(3, R.drawable.recipe_three, getString(R.string.recipe_three)))
        posts.add(Post(4, R.drawable.recipe_four, getString(R.string.recipe_four)))
        posts.add(Post(5, R.drawable.recipe_one, getString(R.string.recipe_one)))
        posts.add(Post(6, R.drawable.recipe_two, getString(R.string.recipe_two)))
        posts.add(Post(7, R.drawable.recipe_three, getString(R.string.recipe_three)))
        posts.add(Post(8, R.drawable.recipe_four, getString(R.string.recipe_four)))

        postAdapter = PostAdapter(requireContext(), posts)
        postAdapter.setListener(this)
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        view.rv_posts.adapter = postAdapter
        return view
    }

    override fun onItemClickListener(position: Int, post: Post) {
//        activity?.let {
//            val intent = Intent(requireContext(), PostDetailsActivity::class.java)
//            it.startActivity(intent)
//        }

        activity?.supportFragmentManager.apply {
            startActivity(Intent(requireActivity(), PostDetailsActivity::class.java))
        }
//        val intent = Intent(activity, PostDetailsActivity::class.java)
//        startActivity(intent)
    }
}
