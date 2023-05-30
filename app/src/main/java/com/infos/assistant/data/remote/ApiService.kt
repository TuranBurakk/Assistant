package com.infos.assistant.data.remote

import com.infos.assistant.data.ChatGptData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("v1/chat/completions")
    suspend fun chat(
        @Header("Content-Type")authHeader:String,
        @Body request : RequestData
    ):ChatGptData

}