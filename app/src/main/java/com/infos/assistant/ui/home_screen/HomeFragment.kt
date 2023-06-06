package com.infos.assistant.ui.home_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.infos.assistant.data.remote.RequestMessage
import com.infos.assistant.databinding.FragmentHomeBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val list = mutableListOf<RequestMessage>()

    override fun onStart() {
        super.onStart()
        showToolBar()
        showBottomBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendBtn.setOnClickListener {
            if (!binding.messageEditText.text.isNullOrEmpty()) {

                val request = binding.messageEditText.text.toString()
                val role = "user"
                val message = RequestMessage(role,request)
                list.add(message)
                viewModel.chat(list)

            } else Toast.makeText(requireContext(),"Message can not be empty",Toast.LENGTH_LONG).show()


        }


    }
}
