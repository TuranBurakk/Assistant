package com.infos.assistant.ui.todo_screen

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.infos.assistant.data.TodoData
import com.infos.assistant.databinding.TodoRowBinding

class TodoAdapter(
    private val clickListener: TaskClickListener
) : Adapter<TodoAdapter.TodoHolder>() {

    private var list = emptyList<TodoData>()

    class TodoHolder(val binding: TodoRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = list[position]
        holder.binding.apply {
            todoTitleTv.text = todo.title
            todoDescTv.text = todo.explanation
            if (todo.done){
                doneBox.isChecked = true
                todoTitleTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                todoDescTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            deleteButton.setOnClickListener {
                clickListener.delete(todo)
            }
            doneBox.setOnClickListener {
                clickListener.completed(todo)
            }
            editButton.setOnClickListener {
                editButton.visibility = View.GONE
                doneButton.visibility = View.VISIBLE
                todoDescTv.visibility = View.GONE
                editDescEt.visibility = View.VISIBLE
            }

            doneButton.setOnClickListener {
                editButton.visibility = View.VISIBLE
                doneButton.visibility = View.GONE
                todoDescTv.visibility = View.VISIBLE
                editDescEt.visibility = View.GONE
                val updateTask = editDescEt.text.toString()
                clickListener.edit(todo,updateTask)
            }
        }


    }

    override fun getItemCount() = list.size

    fun setData(newList: List<TodoData>) {
        list = newList
        notifyDataSetChanged()
    }
}