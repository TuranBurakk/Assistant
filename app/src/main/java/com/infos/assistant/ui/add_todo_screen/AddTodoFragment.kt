package com.infos.assistant.ui.add_todo_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.infos.assistant.data.TodoData
import com.infos.assistant.databinding.FragmentAddTodoBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddTodoFragment : BaseFragment<FragmentAddTodoBinding>(FragmentAddTodoBinding::inflate) {


    private val viewModel: AddTodoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTodoButton.setOnClickListener {
            if (!binding.todoET.text.isNullOrEmpty()){

                if (!binding.todoDesc.text.isNullOrEmpty()){

                    if (!binding.todoDesc.text.isNullOrEmpty()) {
                        val calendar = GregorianCalendar()
                        calendar.set(binding.datePicker.year, binding.datePicker.month, binding.datePicker.dayOfMonth)
                        val date = calendar.time
                        val title = binding.todoET.text.toString()
                        val explanation = binding.todoDetailET.text.toString()
                        val todo = TodoData(title = title, explanation = explanation, date = date)
                        viewModel.addTodo(todo, requireContext())
                        findNavController().navigate(AddTodoFragmentDirections.actionAddTodoFragmentToTodoFragment())
                    } else  Toast.makeText(requireContext(),"Explanations cannot be empty",Toast.LENGTH_LONG).show()

                }else Toast.makeText(requireContext(),"Title cannot be empty",Toast.LENGTH_LONG).show()
        }

    }

}
    }


