package com.infos.assistant.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RequestData(
    val model : String?,
    val messages : List<RequestMessage>?
)

@Parcelize
data class RequestMessage(
    val role : String?,
    val content : String?
        ) : Parcelable
