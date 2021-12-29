package com.pittodo.ui.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pittodo.databinding.RecyclerviewSingleTodoBinding
import com.pittodo.model.ToDoItem

class ToDoAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ToDoItem, ToDoAdapter.ToDoViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            RecyclerviewSingleTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(todo)
        }
        holder.bind(todo)
    }

    override fun getItemId(position: Int): Long {
        val todo = getItem(position)
        return todo.id.toLong()
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [ToDoItem]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ToDoItem>() {
        override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.id == newItem.id
        }
    }


    /**
     * The ToDoViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which gives it access to the full [ToDoItem] information.
     */
    class ToDoViewHolder(private var binding: RecyclerviewSingleTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: ToDoItem) {
            binding.todoitem = todo
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (task: ToDoItem) -> Unit) {
        fun onClick(task: ToDoItem) = clickListener(task)

    }
}

