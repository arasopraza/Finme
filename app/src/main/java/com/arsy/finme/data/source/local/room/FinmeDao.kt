package com.arsy.finme.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arsy.finme.data.source.local.entity.UserEntity

@Dao
interface FinmeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(users: UserEntity)

    @Query("SELECT * FROM user_entities WHERE username = :username")
    fun loginUser(username: String): UserEntity
}