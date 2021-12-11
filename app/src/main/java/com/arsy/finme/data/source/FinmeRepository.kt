package com.arsy.finme.data.source

import androidx.lifecycle.LiveData
import com.arsy.finme.data.source.local.LocalDataSource
import com.arsy.finme.data.source.local.entity.UserEntity
import com.arsy.finme.utils.AppExecutors

class FinmeRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : FinmeDataSource {

    companion object {
        @Volatile
        private var instance: FinmeRepository? = null
        fun getInstance(
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FinmeRepository =
            instance ?: synchronized(this) {
                instance ?: FinmeRepository(localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun insertUser(user: UserEntity) {
        return appExecutors.diskIO().execute { localDataSource.insertUser(user) }
    }

    override suspend fun loginUser(username: String): UserEntity {
        return localDataSource.loginUser(username)
    }
}