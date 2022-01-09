package com.example.homemade.feature.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homemade.R
import com.example.homemade.feature.forgetPassword.view.ForgetPasswordActivity
import com.example.homemade.feature.home.view.HomeActivity
import com.example.homemade.feature.signup.view.SignUpActivity
import com.example.homemade.utils.storage.SavedPreference
import com.facebook.*
import com.facebook.FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var btnFacebook: LoginButton
    private lateinit var callBackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        setEventsListener()
        auth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(applicationContext)
        setAutoLogAppEventsEnabled(true)
        ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY
        callBackManager = CallbackManager.Factory.create()
        btnFacebook = btn_facebook
        btnFacebook.setPermissions("email")
        btnFacebook.registerCallback(
            callBackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result!!.accessToken)
                }

                override fun onCancel() {
                    Toast.makeText(this@SignInActivity, " Canceled", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException?) {
                    Toast.makeText(this@SignInActivity, error!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        Log.d("TAG", "handleFacebookAccessToken:" + accessToken)
        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.getException())
                    val user = auth.currentUser
                    startActivity(Intent(this, HomeActivity::class.java))
                    Toast.makeText(
                        this, "Success. ${user?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Suucess", "handleFacebookAccessToken: ${user?.email}")
                }
            }
    }

    private fun initProperties() {
        auth = FirebaseAuth.getInstance()
        email = ed_email_signIn.text.toString()
        password = ed_password_signIn.text.toString()
    }

    private fun setEventsListener() {
        btn_signup_signIn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        tv_forget_password.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }
        btn_signIn.setOnClickListener {
            userSignIn()
        }
    }

    private fun userSignIn() {
        initProperties()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { p0 ->
                    if (p0.isSuccessful) {
                        Log.e("===", "userSignI: a, $p0")
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                        SavedPreference.getEmail(this)
                    } else {
                        Log.e("===", "userSignI: a, $p0")
                    }
                }
                .addOnFailureListener {
                    Log.e("===", "addOnFailureListener: ${it.message}")
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager.onActivityResult(requestCode, resultCode, data)
    }
}
