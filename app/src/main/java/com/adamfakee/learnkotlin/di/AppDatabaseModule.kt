package com.adamfakee.learnkotlin.di

import android.content.Context
import androidx.room.Room
import com.adamfakee.learnkotlin.data.local.AppDatabaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase (@ApplicationContext context: Context): AppDatabaseManager {
        return Room.databaseBuilder(context, AppDatabaseManager::class.java, "learn_kotlin_database").build()
    }
}