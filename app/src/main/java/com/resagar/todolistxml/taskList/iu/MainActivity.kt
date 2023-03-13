package com.resagar.todolistxml.taskList.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.resagar.todolistxml.databinding.ActivityMainBinding
import com.resagar.todolistxml.databinding.LayoutBottomSheetAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var taskListAdapter: TaskListAdapter

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var bottomSheet: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.bind(binding.root)!!
        binding.lifecycleOwner = this
        binding.taskViewModel = taskViewModel
        setContentView(binding.root)

        initRecyclerView()
        showBottomSheetAddTask()

        taskViewModel.apply {
            taskIsEmpty.observe(this@MainActivity){
                if (it == true) {
                    Toast.makeText(this@MainActivity, "field is empty", Toast.LENGTH_SHORT).show()
                }
            }
            closeAddTaskDialog.observe(this@MainActivity) {
                if(it == true){
                    bottomSheet.dismiss()
                }else{
                    bottomSheet.show()
                }
            }
        }

    }

    private fun initRecyclerView(){
        taskViewModel.getTasks()
        taskListAdapter.apply {
            build(taskViewModel)
            setHasStableIds(true)
            taskViewModel.taskList.observe(this@MainActivity) {
                differ.submitList(it)
            }
        }
        binding.taskList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskListAdapter
        }
    }

    private fun showBottomSheetAddTask(){
        var sheetBinding = LayoutBottomSheetAddTaskBinding.inflate(layoutInflater)
        sheetBinding = DataBindingUtil.bind(sheetBinding.root)!!
        sheetBinding.viewModel = taskViewModel
        sheetBinding.lifecycleOwner = this
        bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(sheetBinding.root)
    }
}