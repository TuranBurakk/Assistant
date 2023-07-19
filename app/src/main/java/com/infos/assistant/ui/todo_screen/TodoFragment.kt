package com.infos.assistant.ui.todo_screen

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.data.TodoData
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentTodoBinding
import java.util.*


class TodoFragment : BaseFragment<FragmentTodoBinding>(FragmentTodoBinding::inflate),TaskClickListener {


    private val viewModel: TodoViewModel by viewModels()
    private val adapter by lazy { TodoAdapter(this) }
    private val args: TodoFragmentArgs by navArgs()

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
                if (args.date == ""){
                    val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                    val currentTime = Calendar.getInstance().time
                    val currentFormattedDate = dateFormatter.format(currentTime)
                    val filterList = viewModel.timeFilter(it,currentFormattedDate)
                    adapter.setData(filterList)
                }else{
                    val filterList = viewModel.timeFilter(it, args.date!!)
                    adapter.setData(filterList)
                }


            }
        }

        binding.hideTv.setOnClickListener {
            binding.hideTv.visibility = View.GONE
            binding.showTv.visibility = View.VISIBLE
            viewModel.todo.observe(viewLifecycleOwner){
                if (it != null){
                    if (args.date == ""){
                        val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                        val currentTime = Calendar.getInstance().time
                        val currentFormattedDate = dateFormatter.format(currentTime)
                        val filterList = viewModel.compliteFilter(it,currentFormattedDate)
                        adapter.setData(filterList)
                    }else{
                        val filterList = viewModel.compliteFilter(it,args.date!!)
                        adapter.setData(filterList)
                    }


                }
            }
        }

        binding.showTv.setOnClickListener {
            binding.hideTv.visibility = View.VISIBLE
            binding.showTv.visibility = View.GONE
            viewModel.todo.observe(viewLifecycleOwner){
                if (it != null){
                    if (args.date == ""){
                        val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                        val currentTime = Calendar.getInstance().time
                        val currentFormattedDate = dateFormatter.format(currentTime)
                        val filterList = viewModel.timeFilter(it,currentFormattedDate)
                        adapter.setData(filterList)
                    }else{
                        val filterList = viewModel.timeFilter(it,args.date!!)
                        adapter.setData(filterList)
                    }


                }
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