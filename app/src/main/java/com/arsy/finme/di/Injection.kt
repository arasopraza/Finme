package com.arsy.finme.di

import android.content.Context
import com.arsy.finme.data.source.FinmeRepository
import com.arsy.finme.data.source.local.LocalDataSource
import com.arsy.finme.data.source.local.room.FinmeDatabase
import com.arsy.finme.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FinmeRepository {

        val database = FinmeDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.finmeDao())
        val appExecutors = AppExecutors()

        return FinmeRepository.getInstance(localDataSource, appExecutors)
    }
}