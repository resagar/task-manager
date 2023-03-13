package com.resagar.todolistxml.taskList.iu

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resagar.todolistxml.taskList.domain.Task
import com.resagar.todolistxml.taskList.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel(), Observable {
    val taskList = MutableLiveData<List<Task>>()
    val taskIsEmpty = MutableLiveData<Boolean>()
    val closeAddTaskDialog = MutableLiveData<Boolean>()

    @Bindable
    val task = MutableLiveData<String>()
    @Bindable
    val status = MutableLiveData<String>()
    @Bindable
    val author = MutableLiveData<String>()

    fun getTasks(){
        viewModelScope.launch {
            val list = taskRepository.getAllTask()
            taskList.value = list
        }
    }

    fun saveNewTask() {
        if(task.value.isNullOrEmpty() && status.value.isNullOrEmpty() && author.value.isNullOrEmpty()) {
            taskIsEmpty.postValue(true)
            return
        }
        taskIsEmpty.postValue(false)
        viewModelScope.launch {
            taskRepository.taskSave(Task(task = task.value!!, author = author.value!!, status = status.value!!))
            val list = taskRepository.getAllTask()
            taskList.postValue(list)
            task.value = ""
            author.value = ""
            status.value = ""
        }
        closeAddTaskDialog.postValue(true)
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.taskDelete(task)
            val list = taskRepository.getAllTask()
            taskList.postValue(list)
        }
    }

    fun showBottomSheet(){
        closeAddTaskDialog.postValue(false)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}