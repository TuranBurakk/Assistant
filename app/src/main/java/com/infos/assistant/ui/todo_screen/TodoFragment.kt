package com.infos.assistant.ui.todo_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.data.TodoData
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentTodoBinding


class TodoFragment : BaseFragment<FragmentTodoBinding>(FragmentTodoBinding::inflate),TaskClickListener {


    private val viewModel: TodoViewModel by viewModels()
    private val adapter by lazy { TodoAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.setData(viewModel.todoList)

    }

    override fun completed(todo: TodoData) {
        viewModel.complete(todo)
    }

    override fun delete(todo: TodoData) {
       viewModel.deleteTask(todo)
    }

}