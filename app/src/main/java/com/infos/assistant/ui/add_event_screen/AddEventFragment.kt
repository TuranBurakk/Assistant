package com.infos.assistant.ui.add_event_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.infos.assistant.databinding.FragmentAddEventBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
        binding.dateTv.text = args.date
        binding.addEventBt.setOnClickListener {
            if (!binding.eventTitleEt.text.isNullOrEmpty()){
                if (!binding.eventDescEt.text.isNullOrEmpty()){
                    val title = binding.eventTitleEt.text.toString()
                    val desc = binding.eventDescEt.text.toString()
                    val date = args.date.toLong()
                }
            }
        }

    }


}