package com.adamfakee.learnkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adamfakee.learnkotlin.feature.local.data.TodoDao
import com.adamfakee.learnkotlin.feature.local.data.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabaseManager : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}