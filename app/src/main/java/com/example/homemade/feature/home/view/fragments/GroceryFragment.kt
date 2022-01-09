package com.example.homemade.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.homemade.R
import kotlinx.android.synthetic.main.activity_home.*

class GroceryFragment : Fragment(R.layout.fragment_grocery){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_grocery, container, false)
        return view
    }
}
