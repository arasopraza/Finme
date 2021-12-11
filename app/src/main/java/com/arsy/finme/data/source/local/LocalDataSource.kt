package com.arsy.finme.data.source.local

import androidx.lifecycle.LiveData
import com.arsy.finme.data.source.local.entity.UserEntity
import com.arsy.finme.data.source.local.room.FinmeDao

class LocalDataSource private constructor(private val dao: FinmeDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(finmeDao: FinmeDao): LocalDataSource =
                INSTANCE ?: LocalDataSource(finmeDao)
    }

    fun insertUser(user: UserEntity) = dao.insertUser(user)
    fun loginUser(username: String): UserEntity = dao.loginUser(username)
}