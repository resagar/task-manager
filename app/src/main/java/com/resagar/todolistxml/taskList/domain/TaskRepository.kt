package com.resagar.todolistxml.taskList.domain

import com.resagar.todolistxml.data.database.task.TaskDao
import com.resagar.todolistxml.data.database.task.toTask
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
   suspend fun getAllTask(): List<Task> =  taskDao.getAll().map { it.toTask() }
   suspend fun taskSave(task: Task) = taskDao.insert(task.toTaskEntity())
   suspend fun taskDelete(task: Task) = taskDao.delete(task.toTaskEntity())
}