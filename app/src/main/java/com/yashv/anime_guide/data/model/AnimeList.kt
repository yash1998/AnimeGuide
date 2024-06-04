package com.yashv.anime_guide.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AnimeList(
    @SerializedName("data")
    val animeData: List<AnimeData?>?,
    @SerializedName("meta")
    val animeMeta: AnimeMeta?
)