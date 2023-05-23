package com.infos.assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class AccountingData(
    val amount : Int?,
    val date : Date?,
    val description : String?
):Parcelable{
    constructor() : this(null,null,null)
}
