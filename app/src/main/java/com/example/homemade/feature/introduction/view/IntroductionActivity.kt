package com.example.homemade.feature.introduction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.signin.view.SignInActivity
import com.example.homemade.feature.signup.view.SignUpActivity
import kotlinx.android.synthetic.main.activity_introduction.*

class IntroductionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        setEventsListener()
    }

    private fun setEventsListener() {
        sign_in_intro.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        sign_up_intro.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
