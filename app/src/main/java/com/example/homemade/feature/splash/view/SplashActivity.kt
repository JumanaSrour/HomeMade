package com.example.homemade.feature.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.home.view.HomeActivity
import com.example.homemade.feature.introduction.view.IntroductionActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initProperties()
        setFlags()
        setHandler()
    }

    private fun setHandler() {
        handler.postDelayed({
            checkUserLoggedIn()
        }, 1000)
    }

    private fun checkUserLoggedIn() {
        if (auth.currentUser != null) {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, IntroductionActivity::class.java))
            finish()
        }
    }

    private fun setFlags() {
        this.window.setFlags(
            WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,
            WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW
        )
    }

    private fun initProperties() {
        handler = Handler()
        auth = FirebaseAuth.getInstance()
    }
}
