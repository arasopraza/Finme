package com.arsy.finme.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arsy.finme.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var bindingHome: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)

        val user = Firebase.auth.currentUser
        bindingHome.user.text = user?.email
    }
}