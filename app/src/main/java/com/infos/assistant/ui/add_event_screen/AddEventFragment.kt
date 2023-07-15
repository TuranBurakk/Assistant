package com.infos.assistant.ui.add_event_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.infos.assistant.data.TodoData
import com.infos.assistant.databinding.FragmentAddEventBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddEventFragment : BaseFragment<FragmentAddEventBinding>(FragmentAddEventBinding::inflate) {

    private val viewModel : AddEventViewModel by viewModels()
    private val args: AddEventFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        showBottomBar()
        showToolBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stringDate = args.date
        val dateFormatter = android.icu.text.SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
        val formattedDate = dateFormatter.format(stringDate)
        binding.addEventBt.setOnClickListener {
            if (!binding.eventTitleEt.text.isNullOrEmpty()){

                if (!binding.eventDescEt.text.isNullOrEmpty()){

                    val title = binding.eventTitleEt.text.toString()
                    val desc = binding.eventDescEt.text.toString()

                    val event = TodoData(title = title, explanation = desc, date = formattedDate)

                    viewModel.addEvent(event,requireContext())

                }else Toast.makeText(requireContext(),"Description cannot be empty",Toast.LENGTH_LONG).show()

            }else Toast.makeText(requireContext(),"Title cannot be empty",Toast.LENGTH_LONG).show()
        }

    }


}