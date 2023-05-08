package com.infos.assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName:String? = null,
    val Todo: List<TodoData> = emptyList()
):Parcelable