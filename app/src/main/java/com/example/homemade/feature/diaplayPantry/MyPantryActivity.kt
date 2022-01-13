package com.example.homemade.feature.diaplayPantry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.home.view.fragments.HomeFragment
import com.example.homemade.ui.adapters.MyPantryAdapter
import com.example.homemade.ui.models.CustomRecipesDialog
import com.example.homemade.ui.models.Pantry
import kotlinx.android.synthetic.main.activity_my_pantry.*

class MyPantryActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var pantryAdapter: MyPantryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pantry)

        homeFragment = HomeFragment()
        initAdapter()
        eventsListener()
    }

    private fun eventsListener() {
        btn_see_recipes_pantry.setOnClickListener {
            createRecipesDialog()
        }

        btn_add_more.setOnClickListener {
            setCurrentFragment(homeFragment)
        }
    }

    private fun setCurrentFragment(homeFragment: HomeFragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, homeFragment)
                .commit()
        }

    private fun createRecipesDialog() {
        val dialog = CustomRecipesDialog().newInstance(
            "Recipes by",
            "Recipes posted by users",
            "Recipes from the app",
            "Results from the internet "
        )
        dialog.show(supportFragmentManager, "custom recipes fragment")
        dialog.onClickListener(object : CustomRecipesDialog.CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                dialog.dismiss()
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }
        })
    }

    private fun initAdapter() {
        val pantry = arrayListOf<Pantry>()
        pantry.add(Pantry(1, "sour cream"))
        pantry.add(Pantry(2, "tomato"))
        pantry.add(Pantry(3, "pepper"))
        pantryAdapter = MyPantryAdapter(this, pantry)
        rv_pantry.adapter = pantryAdapter
    }
}
