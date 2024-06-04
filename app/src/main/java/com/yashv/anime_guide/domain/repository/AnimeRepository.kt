package com.yashv.anime_guide.domain.repository

import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.data.model.AnimeList
import retrofit2.Response

interface AnimeRepository {
    suspend fun loadAnimeList(pageNum: Int, pageSize: Int): Response<AnimeList>
    suspend fun loadAnimeDetailById(animeId: String): Response<AnimeData>
}