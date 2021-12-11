package com.arsy.finme.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arsy.finme.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class],
        version = 1,
        exportSchema = false)
abstract class FinmeDatabase : RoomDatabase() {
    abstract fun finmeDao(): FinmeDao

    companion object {

        @Volatile
        private var INSTANCE: FinmeDatabase? = null

        fun getInstance(context: Context): FinmeDatabase =
                INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            FinmeDatabase::class.java,
                            "Finme.db"
                    ).build().apply {
                        INSTANCE = this
                    }
                }
    }
}