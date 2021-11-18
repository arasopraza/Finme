package com.arsy.finme.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arsy.finme.ui.home.MainActivity
import com.arsy.finme.ui.home.MainActivity.Companion.EXTRA_NAME
import com.arsy.finme.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        bindingLogin.btnLogin.setOnClickListener {
            val email = bindingLogin.email.text
            var nama: String = email.toString()
            nama = nama.substringBefore("@")

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME, nama)
            startActivity(intent)
        }
    }
}