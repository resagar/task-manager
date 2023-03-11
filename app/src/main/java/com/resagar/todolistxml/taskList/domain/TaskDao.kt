package com.resagar.todolistxml.taskList.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    fun insert(vararg tasks: Task)
    @Query("SELECT * FROM tasks_list")
    fun getAll(): List<Task>
    @Delete
    fun delete(task: Task)
}