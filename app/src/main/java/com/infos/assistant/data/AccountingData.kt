package com.infos.assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountingData(
    var amount : Int?,
    val date : String?,
    val description : String?
):Parcelable{
    constructor() : this(null,null,null)
}
