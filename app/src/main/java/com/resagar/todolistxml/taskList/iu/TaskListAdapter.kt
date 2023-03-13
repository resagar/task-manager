package com.resagar.todolistxml.taskList.iu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.resagar.todolistxml.databinding.ItemTaskListBinding
import com.resagar.todolistxml.taskList.domain.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskListAdapter @Inject constructor(): RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private lateinit var binding: ItemTaskListBinding
    private lateinit var context: Context
    private lateinit var taskViewModel: TaskViewModel
    val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemTaskListBinding.inflate(inflater, parent, false)
        binding = DataBindingUtil.bind(binding.root)!!
        binding.taskViewModel = taskViewModel
        context = parent.context
        val viewHolder = ViewHolder()
        binding.viewHolder = viewHolder
        return viewHolder
    }

    fun build(taskViewModel: TaskViewModel){
        this.taskViewModel = taskViewModel
    }

    override fun getItemId(position: Int): Long = differ.currentList[position].uid.toLong()
    override fun getItemViewType(position: Int): Int = differ.currentList[position].uid

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        lateinit var task: Task
        fun bind(task: Task){
            this.task = task
        }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.uid == newItem.uid
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}