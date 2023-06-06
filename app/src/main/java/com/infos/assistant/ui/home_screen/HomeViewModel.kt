package com.infos.assistant.ui.home_screen

import androidx.lifecycle.ViewModel
import com.infos.assistant.data.remote.RequestMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    fun chat(message:List<RequestMessage>){
        useCase.invoke(message)
    }
}