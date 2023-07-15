package com.infos.assistant.ui.calandar_screen

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.infos.assistant.databinding.FragmentCalendarBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(FragmentCalendarBinding::inflate) {

    private val viewModel: CalendarViewModel by viewModels()


    override fun onStart() {
        super.onStart()
        showToolBar()
        showBottomBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getToDo()
        viewModel.todo.observe(viewLifecycleOwner){
            if (it != null){
                binding.calendarView.setEvents(it)
            }
        }

        binding.calendarView.setOnDayClickListener(object : OnDayClickListener{
            override fun onDayClick(eventDay: EventDay) {
                val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                val date = dateFormatter.format(eventDay.calendar.time)
                findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToTodoFragment())

                binding.addButton.setOnClickListener {
                    findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToAddEventFragment(date))
                }
            }

        })

    }
}