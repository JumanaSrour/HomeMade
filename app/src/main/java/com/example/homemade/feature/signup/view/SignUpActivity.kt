package com.example.homemade.feature.signup.view

import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.home.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String

    private lateinit var username: String

    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setEventsListeners()
    }

    private fun initProperties() {
        auth = FirebaseAuth.getInstance()
        email = ed_email_signup.text.toString()
        password = ed_password_signup.text.toString()
        username = ed_name_signup.text.toString()
    }

    private fun setEventsListeners() {
        btn_signup.setOnClickListener {
            Log.e("*******", "setEventsListeners")
            userSignUp()
        }
    }

    private fun userSignUp() {
        initProperties()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { p0 ->
                    if (p0.isSuccessful) {

                        val uid = p0.result.user!!.uid
                        val ref = userCollectionRef.document(uid)

                        val param = ArrayMap<String, Any>()
                        param["username"] = username
                        param["email"] = email
                        param["user_id"] = uid

                        ref.set(param)

                        Log.e("===", "onComplete: $p0")
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Log.e("===", "onComplete: ")
                    }
                }.addOnFailureListener {
                    Log.e("===", "addOnFailureListener: ${it.message}")
                }
        }
    }
}
