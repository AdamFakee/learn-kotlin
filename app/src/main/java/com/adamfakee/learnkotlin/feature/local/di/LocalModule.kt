package com.adamfakee.learnkotlin.feature.local.di

import android.content.Context
import androidx.room.Room
import com.adamfakee.learnkotlin.data.local.AppDatabaseManager
import com.adamfakee.learnkotlin.feature.local.data.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabaseManager): TodoDao {
        return database.todoDao()
    }
}
