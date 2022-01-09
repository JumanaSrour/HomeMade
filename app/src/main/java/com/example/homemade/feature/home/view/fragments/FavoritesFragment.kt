package com.example.homemade.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homemade.R
import com.example.homemade.ui.adapters.PostAdapter
import com.example.homemade.ui.models.Post
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment(R.layout.fragment_favorites){
    private lateinit var postAdapter: PostAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val posts = arrayListOf<Post>()
        posts.add(Post(1,R.drawable.recipe_one, getString(R.string.recipe_one)))
        posts.add(Post(2, R.drawable.recipe_two, getString(R.string.recipe_two)))
        posts.add(Post(3, R.drawable.recipe_three, getString(R.string.recipe_three)))
        posts.add(Post(4, R.drawable.recipe_four, getString(R.string.recipe_four)))
        posts.add(Post(5,R.drawable.recipe_one, getString(R.string.recipe_one)))
        posts.add(Post(6, R.drawable.recipe_two, getString(R.string.recipe_two)))
        posts.add(Post(7, R.drawable.recipe_three, getString(R.string.recipe_three)))
        posts.add(Post(8, R.drawable.recipe_four, getString(R.string.recipe_four)))


        postAdapter = PostAdapter(requireContext(), posts)
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        view.rv_favorites.adapter = postAdapter
        return view
    }
}
