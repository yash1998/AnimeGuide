package com.yashv.anime_guide.data.remote

import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.data.model.AnimeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {

    @GET("/anime")
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<AnimeList>

    @GET("/anime/by-id/{id}")
    suspend fun getAnimeDetailById(@Path("id") animeId: String): Response<AnimeData>

}