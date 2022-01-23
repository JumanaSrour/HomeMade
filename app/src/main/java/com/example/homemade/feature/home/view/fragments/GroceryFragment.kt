package com.example.homemade.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homemade.R
import com.example.homemade.ui.adapters.GroceryAdapter
import com.example.homemade.ui.models.Grocery
import kotlinx.android.synthetic.main.fragment_grocery.view.*

class GroceryFragment : Fragment() {
    private lateinit var groceryAdapter: GroceryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val grocery = arrayListOf<Grocery>()
        grocery.add(Grocery(1, getString(R.string.potato)))
        grocery.add(Grocery(2, getString(R.string.tomato)))
        grocery.add(Grocery(3, getString(R.string.eggplant)))

        val view = inflater.inflate(R.layout.fragment_grocery, container, false)
        groceryAdapter = GroceryAdapter(requireContext(), grocery)
        view.rv_grocery.adapter = groceryAdapter
//        view.rg_grocery.setOnCheckedChangeListener { radioGroup, checkedId ->
//            when (checkedId) {
//                R.id.rb_potato -> {
//                    rb_potato.visibility = View.VISIBLE
//                }
//            }
//        }
        return view
    }
}
