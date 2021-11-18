package com.arsy.finme.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arsy.finme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingHome: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)

        bindingHome.user.text = intent.getStringExtra(EXTRA_NAME)
    }

    companion object {
        const val EXTRA_NAME = "name"
    }
}