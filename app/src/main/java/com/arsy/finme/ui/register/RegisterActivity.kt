package com.arsy.finme.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arsy.finme.databinding.ActivityRegisterBinding
import com.arsy.finme.ui.home.MainActivity
import com.arsy.finme.ui.login.LoginViewModel
import com.arsy.finme.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var bindingRegister: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegister = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        initObservable()

        bindingRegister.btnRegister.setOnClickListener {
            val email = bindingRegister.email.text.toString()
            val password = bindingRegister.password.text.toString()

            viewModel.register(email, password)
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun initObservable(){
        viewModel.registerStatus.observe(this, { success ->
            if (success == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Register failed.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}