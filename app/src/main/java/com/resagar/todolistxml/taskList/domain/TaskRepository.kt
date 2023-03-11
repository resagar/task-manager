package com.resagar.todolistxml.taskList.domain

import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
   fun getAllTask(): List<Task> = taskDao.getAll()
   fun taskSave(task: Task) = taskDao.insert(task)
   fun taskDelete(task: Task) = taskDao.delete(task)
}