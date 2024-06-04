package com.yashv.anime_guide.data.repository

import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.data.model.AnimeList
import com.yashv.anime_guide.data.remote.AnimeApiService
import com.yashv.anime_guide.domain.repository.AnimeRepository
import retrofit2.Response
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApiService: AnimeApiService
) : AnimeRepository {
    override suspend fun loadAnimeList(pageNum: Int, pageSize: Int): Response<AnimeList> {
        return animeApiService.getAnimeList(pageNum, pageSize)
    }

    override suspend fun loadAnimeDetailById(animeId: String): Response<AnimeData> {
        return animeApiService.getAnimeDetailById(animeId)
    }
}