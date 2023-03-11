package com.resagar.todolistxml.taskList.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_list")
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "author") val author: String
)
