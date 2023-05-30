package com.infos.assistant.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatGptData(
    @SerializedName("id")
    val id : String?,
    @SerializedName("object")
    val Object: String?,
    @SerializedName("created")
    val created : Int?,
    @SerializedName("model")
    val model : String?,
    @SerializedName("usage")
    val usage : UsageData,
    @SerializedName("choices")
    val choices : List<ChoicesData?>?
): Parcelable

@Parcelize
data class UsageData(
    @SerializedName("prompt_tokens")
    val promptToken : Int?,
    @SerializedName("completion_tokens")
    val completionToken : Int?,
    @SerializedName("total_tokens")
    val totalToken:Int?
): Parcelable

@Parcelize
data class ChoicesData(
    @SerializedName("message")
    val message : MessageData?,
    @SerializedName("finish_reason")
    val finish : String?
):Parcelable

@Parcelize
data class MessageData(
    @SerializedName("role")
    val role : String?,
    @SerializedName("content")
    val content : String?
):Parcelable