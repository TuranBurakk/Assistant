package com.infos.assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName:String? = null,
    val todo: List<TodoData> = emptyList(),
    val accounting : List<AccountingData> = emptyList(),
):Parcelable