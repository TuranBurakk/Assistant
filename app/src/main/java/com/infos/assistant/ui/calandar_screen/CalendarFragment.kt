package com.infos.assistant.ui.calandar_screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(FragmentCalendarBinding::inflate) {
    override fun onStart() {
        super.onStart()
        showToolBar()
        showBottomBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = GregorianCalendar(year, month, dayOfMonth).time.toString()
            binding.addButton.setOnClickListener {
                findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToAddEventFragment(date))
            }
        }

    }
}