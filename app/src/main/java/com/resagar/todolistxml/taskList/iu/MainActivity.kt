package com.resagar.todolistxml.taskList.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.resagar.todolistxml.databinding.ActivityMainBinding
import com.resagar.todolistxml.taskList.domain.Task
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var taskListAdapter: TaskListAdapter

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var textField: EditText
    private lateinit var btnAdd: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.apply {
            btnAdd = addTask
            textField = eNewTask
        }
        btnAdd.setOnClickListener { onClickAddTask() }
    }

    private fun initRecyclerView(){
        taskViewModel.getTasks()
        taskListAdapter.apply {
            deleteTask = { onClickDelete(it) }
            setHasStableIds(true)
            differ.submitList(taskViewModel.taskList.value!!)
        }
        binding.taskList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskListAdapter
        }
    }

    private fun onClickAddTask(){
        if(textField.text.isNullOrEmpty()) {
            Toast.makeText(this, "field new task empty", Toast.LENGTH_SHORT).show()
            return
        }
        taskViewModel.apply {
            saveNewTask(textField.text.toString())
            taskListAdapter.differ.submitList(taskList.value!!)
        }
        textField.setText("")
    }

    private fun onClickDelete(task: Task) {
        taskViewModel.apply {
            deleteTask(task)
            taskListAdapter.differ.submitList(taskList.value!!)
        }
    }

}