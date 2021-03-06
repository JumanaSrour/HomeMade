package com.example.homemade.feature.forgetPassword.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.signin.view.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        setEventsListeners()
    }

    private fun initProperties() {
        auth = FirebaseAuth.getInstance()
        email = ed_email_forgetPassword.text.toString()
    }

    private fun setEventsListeners() {
        btn_forgetPassword.setOnClickListener {
            sendVerificationEmail()
        }
        back_forgetPassword.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun sendVerificationEmail() {
        initProperties()
        if (email.isNotEmpty()) {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { p0 ->
                    val error = p0.exception.toString()
                    if (p0.isSuccessful) {
                        Log.e("===", "sendVerificationEmail: $error",)
                        Toast.makeText(this, "email sent to $email\"", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .addOnFailureListener {
                    Log.e("===", "sendVerificationEmail: ${it.message}",)
                }
        }
    }
}
