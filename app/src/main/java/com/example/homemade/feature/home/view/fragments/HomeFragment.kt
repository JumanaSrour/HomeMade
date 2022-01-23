package com.example.homemade.feature.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homemade.R
import com.example.homemade.feature.diaplayPantry.MyPantryActivity
import com.example.homemade.ui.adapters.CategoryAdapter
import com.example.homemade.ui.models.Category
import com.example.homemade.ui.models.CustomAddIngredientDialog
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.SetClickListener {
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        eventsListener()
        val categories = arrayListOf<Category>()
        categories.add(Category(1, category_image = R.drawable.category_diary, "Diary", 3))
        categories.add(Category(2, category_image = R.drawable.category_fruits, "Fruits", 4))
        categories.add(Category(3, category_image = R.drawable.category_vegetables, "Vegetables", 5))

        categoryAdapter = CategoryAdapter(requireContext(), categories)
        categoryAdapter.setListener(this)
        categoryAdapter.setListener(object : CategoryAdapter.SetClickListener {
            override fun onItemClickListener(position: Int, category: Category) {
                Log.d("TAG", "onItemClickListener:clicked the card")
            }

            override fun onButtonClickListener(position: Int, button: Button) {
                createAddIngredientDialog()
            }
        })
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.rv_home.layoutManager = LinearLayoutManager(activity)
        view.rv_home.adapter = categoryAdapter

        view.btn_my_pantry.setOnClickListener {
            startActivity(Intent(requireContext(), MyPantryActivity::class.java))
        }
        return view
    }

    private fun createAddIngredientDialog() {
        val dialog = CustomAddIngredientDialog().newInstance(
            "C",
            "Add ingredient",
            "Add",
            "Add"
        )
        dialog.show(requireFragmentManager(), "custom ingredients fragment")
        dialog.onClickListener(object : CustomAddIngredientDialog.CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                dialog.dismiss()
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }
        })
    }

    override fun onItemClickListener(position: Int, category: Category) {
        Log.d("TAG", "onItemClickListener:clicked the card")
    }

    override fun onButtonClickListener(position: Int, button: Button) {
        createAddIngredientDialog()
    }
}
