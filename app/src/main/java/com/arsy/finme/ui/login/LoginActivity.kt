package com.arsy.finme.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arsy.finme.ui.home.MainActivity
import com.arsy.finme.ui.home.MainActivity.Companion.EXTRA_NAME
import com.arsy.finme.databinding.ActivityLoginBinding
import com.arsy.finme.ui.register.RegisterActivity
import com.arsy.finme.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        val factory = ViewModelFactory.getInstance(this)
        val mViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        bindingLogin.btnLogin.setOnClickListener {
            val email = bindingLogin.email.text.toString()
            val password = bindingLogin.password.text.toString()

            mViewModel.inputUsername.postValue(email)
            mViewModel.inputPassword.postValue(password)

            mViewModel.authUser()
            

//            mViewModel.errorToast.observe(this, { hasError ->
//                if (hasError == true) {
//                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//                    mViewModel.doneToast()
//                }
//            })

            mViewModel.navigateToHome.observe(this, { auth ->
                if (auth == true) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(EXTRA_NAME, email)
                    startActivity(intent)
                }
            })
        }

        bindingLogin.tvCreate.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}