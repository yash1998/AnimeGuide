package com.yashv.anime_guide.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AnimeData(
    @SerializedName("alternativeTitles")
    val alternativeTitles: List<String?>?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("genres")
    val genres: List<String?>?,
    @SerializedName("hasEpisode")
    val hasEpisode: Boolean?,
    @SerializedName("hasRanking")
    val hasRanking: Boolean?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("ranking")
    val ranking: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("thumb")
    val thumb: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
): Parcelable