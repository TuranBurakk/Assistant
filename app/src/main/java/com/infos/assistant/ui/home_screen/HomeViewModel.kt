package com.infos.assistant.ui.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infos.assistant.data.ChatGptData
import com.infos.assistant.data.remote.RequestData
import com.infos.assistant.utlis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val _response = MutableLiveData<ChatGptData?>()
    val response: LiveData<ChatGptData?> = _response

    fun sendMessage(request: RequestData) {
        chat(request)
    }

    private fun chat(message: RequestData) {
        useCase.invoke(message).onEach { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    _response.value = result.data
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}