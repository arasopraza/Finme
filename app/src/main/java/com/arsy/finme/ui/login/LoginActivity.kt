package com.arsy.finme.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arsy.finme.databinding.ActivityLoginBinding
import com.arsy.finme.ui.home.MainActivity
import com.arsy.finme.ui.register.RegisterActivity
import com.arsy.finme.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var bindingLogin: ActivityLoginBinding
    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        val factory = ViewModelFactory.getInstance(this)
        mViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        auth = Firebase.auth

        observerLoading()
        bindingLogin.btnLogin.setOnClickListener {
            val email = bindingLogin.email.text.toString()
            val password = bindingLogin.password.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Email atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                signIn(email, password)
            }
        }

        bindingLogin.tvCreate.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        mViewModel.signIn(email, password)
        mViewModel.signInStatus.observe(this, { success ->
            if (success == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observerLoading() {
        mViewModel.loading.observe(this, {
            if (!it) {
                bindingLogin.progressBar.visibility = View.GONE
            } else {
                bindingLogin.progressBar.visibility = View.VISIBLE
            }
        })
    }
}