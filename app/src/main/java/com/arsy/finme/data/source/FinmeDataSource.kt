package com.arsy.finme.data.source

import androidx.lifecycle.LiveData
import com.arsy.finme.data.source.local.entity.UserEntity

interface FinmeDataSource {
    fun insertUser(user: UserEntity)
    suspend fun loginUser(username: String): UserEntity
}