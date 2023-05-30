package com.infos.assistant.ui.home_screen

import com.infos.assistant.data.ChatGptData
import com.infos.assistant.data.remote.RemoteDataSource
import com.infos.assistant.data.remote.RequestMessage
import com.infos.assistant.utlis.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    operator fun invoke(message:List<RequestMessage>): Flow<Resource<ChatGptData>> = flow {
        try {
            emit(Resource.loading())
            val answer = remoteDataSource.chat(message)
            emit(Resource.success(answer))
        }catch (e: HttpException){
            emit(Resource.error(e.localizedMessage))
        }catch (e : IOException){
            emit(Resource.error("Check your internet connect "))
        }
    }
}