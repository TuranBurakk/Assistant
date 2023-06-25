package com.infos.assistant.data.remote

import com.infos.assistant.data.ChatGptData
import com.infos.assistant.utlis.Constants
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun chat(message: RequestData): ChatGptData {
        return apiService.chat("application/json", "Bearer ${Constants.API_KEY}", message)
    }
}