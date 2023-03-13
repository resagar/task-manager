package com.resagar.todolistxml.data.database.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(vararg taskEntities: TaskEntity)
    @Query("SELECT * FROM tasks_list")
    suspend fun getAll(): List<TaskEntity>
    @Delete
    suspend fun delete(taskEntity: TaskEntity)
}