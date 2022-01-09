package com.example.homemade.ui.models

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.homemade.R
import kotlinx.android.synthetic.main.custom_add_ingredient_dialog.*

class CustomAddIngredientDialog : DialogFragment() {
    private var title: String? = null
    private var btnAdd: String? = null
    private var btnCancel: String? = null
    private var listener: CustomDialogListener? = null

    fun newInstance(
        title: String,
        btnAdd: String,
        btnCancel: String,
    ): CustomAddIngredientDialog {
        val args = Bundle()
        args.putString("title", title)
        args.putString("btnAdd", btnAdd)
        args.putString("btnCancel", btnCancel)
        val fragment = CustomAddIngredientDialog()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_add_ingredient_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        if (!TextUtils.isEmpty(title)) {
            tv_add_ingredient.visibility = View.VISIBLE
            tv_add_ingredient.text = title
        } else {
            tv_add_ingredient.visibility = View.GONE
        }

        if (!TextUtils.isEmpty(btnCancel)) {
            ib_cancel_ingredient.visibility = View.VISIBLE
            ib_cancel_ingredient.text = title
        } else {
            ib_cancel_ingredient.visibility = View.GONE
        }

        if (!TextUtils.isEmpty(btnAdd)) {
            btn_add_ingredient.visibility = View.VISIBLE
            btn_add_ingredient.text = title
        } else {
            btn_add_ingredient.visibility = View.GONE
        }

        btn_add_ingredient.setOnClickListener {
            listener?.onDialogPositiveClick("Success")
        }

        ib_cancel_ingredient.setOnClickListener {
            listener?.onDialogNegativeClick("Failed")
        }
    }

    fun onClickListener(listener: CustomDialogListener) {
        this.listener = listener
    }

    private fun getArgs() {
        arguments?.getString("title").let {
            title = it
        }
        arguments?.getString("btnAdd").let {
            btnAdd = it
        }
        arguments?.getString("btnCancel").let {
            btnCancel = it
        }
    }

    interface CustomDialogListener {
        fun onDialogPositiveClick(str: String)
        fun onDialogNegativeClick(str: String)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
