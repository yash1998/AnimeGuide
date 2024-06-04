package com.yashv.anime_guide.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AnimeMeta(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("totalData")
    val totalData: Int?,
    @SerializedName("totalPage")
    val totalPage: Int?
)