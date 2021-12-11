package com.arsy.finme.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entities")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name= "userId")
        var userId: Int,

        @ColumnInfo(name= "username")
        var username: String,

        @ColumnInfo(name= "password")
        var password: String,
)
