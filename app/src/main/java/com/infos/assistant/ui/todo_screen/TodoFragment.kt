package com.infos.assistant.ui.todo_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.data.TodoData
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentTodoBinding


class TodoFragment : BaseFragment<FragmentTodoBinding>(FragmentTodoBinding::inflate),TaskClickListener {


    private val viewModel: TodoViewModel by viewModels()
    private val adapter by lazy { TodoAdapter(this) }

    override fun onStart() {
        super.onStart()
        hideTextview()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getToDo()
        viewModel.todo.observe(viewLifecycleOwner){
            if (it != null){
              val filterList = viewModel.timeFilter(it)
              adapter.setData(filterList)
            }
        }

        binding.addTodoButton.setOnClickListener {
            findNavController().navigate(TodoFragmentDirections.actionTodoFragmentToAddTodoFragment())
        }
    }
    override fun completed(todo: TodoData) {
        viewModel.complete(todo)
    }

    override fun delete(todo: TodoData) {
       viewModel.deleteTask(todo)
    }

    override fun edit(todo: TodoData, updatedTodo: String) {
        viewModel.editTask(todo,updatedTodo,requireContext())
    }

}