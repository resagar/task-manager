package com.resagar.todolistxml.data.database.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.resagar.todolistxml.taskList.domain.Task

@Entity(tableName = "tasks_list")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "author") val author: String
)

fun TaskEntity.toTask() = Task(uid, task, status, author)

