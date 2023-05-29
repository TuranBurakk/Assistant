package com.infos.assistant.ui.home_screen

import android.os.Bundle
import android.view.View
import com.infos.assistant.databinding.FragmentHomeBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun onStart() {
        super.onStart()
        showToolBar()
        showBottomBar()
        hideTextview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}