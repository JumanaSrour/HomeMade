package com.example.homemade.ui.models

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.homemade.R
import kotlinx.android.synthetic.main.custom_recipes_dilog.*

class CustomRecipesDialog : DialogFragment() {

    private var title: String? = null
    private var btnRecipeUser: String? = null
    private var btnRecipeApp: String? = null
    private var btnRecipeOnline: String? = null
    private var listener: CustomDialogListener? = null

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun newInstance(
        title: String,
        btnRecipeUser: String,
        btnRecipeApp: String,
        btnRecipeOnline: String
    ): CustomRecipesDialog {
        val args = Bundle()
        args.putString("title", title)
        args.putString("btnRecipeUser", btnRecipeUser)
        args.putString("btnRecipeApp", btnRecipeApp)
        args.putString("btnRecipeOnline", btnRecipeOnline)
        val fragment = CustomRecipesDialog()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_recipes_dilog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        if (!TextUtils.isEmpty(title)) {
            tv_alert_title.visibility = View.VISIBLE
            tv_alert_title.text = title
        } else {
            tv_alert_title.visibility = View.GONE
        }

        if (!TextUtils.isEmpty(btnRecipeUser)) {
            btn_alert_users.visibility = View.VISIBLE
            btn_alert_users.text = btnRecipeUser
        } else {
            btn_alert_users.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(btnRecipeApp)) {
            btn_alert_app.visibility = View.VISIBLE
            btn_alert_app.text = btnRecipeApp
        } else {
            btn_alert_app.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(btnRecipeOnline)) {
            btn_alert_online.visibility = View.VISIBLE
            btn_alert_online.text = btnRecipeOnline
        } else {
            btn_alert_online.visibility = View.GONE
        }

//        problem : how to set listener to button
        btn_alert_users.setOnClickListener {
            listener?.onDialogPositiveClick("Success")
        }
        btn_alert_app.setOnClickListener {
            listener?.onDialogPositiveClick("Success")
        }
        btn_alert_online.setOnClickListener {
            listener?.onDialogPositiveClick("Success")
        }
    }

    fun onClickListener(listener: CustomDialogListener) {
        this.listener = listener
    }

    interface CustomDialogListener {
        fun onDialogPositiveClick(str: String)
        fun onDialogNegativeClick(str: String)
    }

    private fun getArgs() {
        arguments?.getString("title")?.let {
            title = it
        }
        arguments?.getString("btnRecipeUser")?.let {
            btnRecipeUser = it
        }
        arguments?.getString("btnRecipeApp")?.let {
            btnRecipeApp = it
        }
        arguments?.getString("btnRecipeOnline")?.let {
            btnRecipeOnline = it
        }
    }
}
