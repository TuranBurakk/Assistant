package com.infos.assistant.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class TodoData(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String?,
    var explanation: String?,
    val date: String?,
    var done: Boolean = false
) : Parcelable {
    constructor() : this(UUID.randomUUID().toString(), null, null, null, false)
}