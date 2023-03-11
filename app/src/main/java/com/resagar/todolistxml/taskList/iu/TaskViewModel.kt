package com.resagar.todolistxml.taskList.iu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resagar.todolistxml.taskList.domain.Task
import com.resagar.todolistxml.taskList.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {
    val taskList = MutableLiveData<MutableList<Task>>()
    val task = MutableLiveData<String>()

    fun getTasks(){
        viewModelScope.launch {
            val list = taskRepository.getAllTask()
            taskList.value = list as MutableList<Task>
        }
    }

    fun saveNewTask(eTask: String) {
        task.value = eTask
        viewModelScope.launch {
            taskRepository.taskSave(Task(task = task.value!!, author = "Rene Garcia", status = "pending"))
        }
        getTasks()
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.taskDelete(task)
        }
        getTasks()
    }
}