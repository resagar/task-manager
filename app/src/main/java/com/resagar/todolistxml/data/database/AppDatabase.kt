package com.resagar.todolistxml.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.resagar.todolistxml.data.database.task.TaskEntity
import com.resagar.todolistxml.data.database.task.TaskDao

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}