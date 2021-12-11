package com.arsy.finme.ui.register

import androidx.lifecycle.ViewModel
import com.arsy.finme.data.source.FinmeRepository
import com.arsy.finme.data.source.local.entity.UserEntity

class RegisterViewModel(private val repository: FinmeRepository): ViewModel() {
    fun insertUser(user: UserEntity) = repository.insertUser(user)
}