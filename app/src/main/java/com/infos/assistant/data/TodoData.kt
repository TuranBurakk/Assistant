package com.infos.assistant.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TodoData(
    val uuid: UUID = UUID.randomUUID(),
    val title : String?,
    val explanation: String?,
    val date: Date?,
    var done:Boolean = false
):Parcelable
