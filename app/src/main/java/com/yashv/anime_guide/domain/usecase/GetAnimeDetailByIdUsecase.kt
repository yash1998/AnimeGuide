package com.yashv.anime_guide.domain.usecase

import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.domain.repository.AnimeRepository
import com.yashv.anime_guide.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeDetailByIdUsecase @Inject constructor(private val repository: AnimeRepository) {
    suspend operator fun invoke(
        animeId: String
    ): Flow<ApiResponse<AnimeData>> = flow {
        emit(ApiResponse.Loading())
        val response = repository.loadAnimeDetailById(animeId)
        if (response.isSuccessful) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Error(response.errorBody().toString()))
        }
    }
}
