package com.resagar.todolistxml.taskList.domain

import com.resagar.todolistxml.data.database.task.TaskEntity

data class Task(
    val uid: Int = 0,
    val task: String,
    val status: String,
    val author: String
)
fun Task.toTaskEntity() = TaskEntity(uid, task, status, author)
