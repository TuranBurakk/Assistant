package com.infos.assistant.ui.home_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.data.MessageDataRv
import com.infos.assistant.data.remote.RequestData
import com.infos.assistant.data.remote.RequestMessage
import com.infos.assistant.databinding.FragmentHomeBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val messageList = mutableListOf<MessageDataRv>()
    private val adapter by lazy { HomeAdapter() }

    override fun onStart() {
        super.onStart()
        showToolBar()
        showBottomBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.sendBtn.setOnClickListener {

            if (!binding.messageEditText.text.isNullOrEmpty()) {
                val request = binding.messageEditText.text.toString()
                val model = "gpt-3.5-turbo"
                val RequestMessage = RequestMessage("user",request)
                val message = RequestData(model, listOf(RequestMessage))
                viewModel.sendMessage(message)
                messageList.add(MessageDataRv(request,"user"))
                binding.messageEditText.text = null

            } else {
                Toast.makeText(requireContext(), "Message can not be empty", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.response.observe(viewLifecycleOwner) { completionResponse ->
            if (completionResponse != null) {
            val response  = completionResponse.choices[0]?.message?.content
            messageList.add(MessageDataRv(response,"bot"))
            }
        }

        adapter.setData(messageList)
        println(messageList)
    }
}
