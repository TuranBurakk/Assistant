package com.infos.assistant.data.remote

import com.infos.assistant.data.ChatGptData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun chat(message:List<RequestMessage>?): ChatGptData {
        val body = RequestData("user",message)
        return  apiService.chat("application/json",body)
    }
}