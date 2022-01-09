package com.example.homemade.feature.home.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homemade.R
import com.example.homemade.feature.addPost.AddPostActivity
import com.example.homemade.feature.home.view.fragments.FavoritesFragment
import com.example.homemade.feature.home.view.fragments.GroceryFragment
import com.example.homemade.feature.home.view.fragments.HomeFragment
import com.example.homemade.feature.home.view.fragments.PostsFragment
import com.example.homemade.feature.profile.view.ProfileActivity
import com.example.homemade.ui.models.User
import com.example.homemade.utils.storage.SavedPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var homeFragment: HomeFragment
    private lateinit var postsFragment: PostsFragment
    private lateinit var favoritesFragment: FavoritesFragment
    private lateinit var groceryFragment: GroceryFragment
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var auth: FirebaseAuth
    private lateinit var userImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initProperties()
        setEventsListener()
        retrieveUser()
        userImage = profile_home
        bottomNavView.setOnNavigationItemSelectedListener(this)
        bottomNavView.selectedItemId = R.id.home
    }

    private fun retrieveUser() {
        val uid = SavedPreference.user_id

        if (uid != "") {
            userCollectionRef.document(uid!!).get()
                .addOnSuccessListener { document ->
                    if (document != null) {

                        val user = document.toObject<User>()
                        Glide.with(this).load(Uri.parse(user!!.user_image)).circleCrop()
                            .into(userImage)
                    } else {
                        Log.d("---", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("---", "get failed with ", exception)
                }
        }
    }

    private fun initProperties() {
        auth = Firebase.auth
        homeFragment = HomeFragment()
        postsFragment = PostsFragment()
        favoritesFragment = FavoritesFragment()
        groceryFragment = GroceryFragment()
    }

    fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
            .commit()
    }

    private fun setEventsListener() {
        profile_home.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        btn_add_post.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                setCurrentFragment(homeFragment)
                tv_ingredients.text = getString(R.string.my_ingredients)
                btn_add_post.visibility = View.GONE
            }
            R.id.posts -> {
                setCurrentFragment(postsFragment)
                tv_ingredients.text = getString(R.string.posts)
                btn_add_post.visibility = View.VISIBLE
            }
            R.id.favorites -> {
                setCurrentFragment(favoritesFragment)
                tv_ingredients.text = getString(R.string.favorites)
                btn_add_post.visibility = View.GONE
            }
            R.id.grocery -> {
                setCurrentFragment(groceryFragment)
                tv_ingredients.text = getString(R.string.shopping_list)
                btn_add_post.visibility = View.GONE
            }
        }
        return true
    }
}
