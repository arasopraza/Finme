package com.arsy.finme.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arsy.finme.data.source.local.entity.UserEntity
import com.arsy.finme.databinding.ActivityRegisterBinding
import com.arsy.finme.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var bindingRegister: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegister = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)

        val factory = ViewModelFactory.getInstance(this)
        val mViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        bindingRegister.btnRegister.setOnClickListener {
            val email = bindingRegister.email.text.toString()
            val password = bindingRegister.password.text.toString()

            val user = UserEntity(0, email, password)
            mViewModel.insertUser(user)
            Toast.makeText(this, "Selamat anda mendapatkan duit 10jt", Toast.LENGTH_SHORT).show()
        }
    }
}