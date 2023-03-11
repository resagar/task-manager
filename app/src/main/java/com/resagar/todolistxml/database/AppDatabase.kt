package com.resagar.todolistxml.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.resagar.todolistxml.taskList.domain.Task
import com.resagar.todolistxml.taskList.domain.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}