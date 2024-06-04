package com.yashv.anime_guide.domain.usecase

import com.yashv.anime_guide.data.model.AnimeList
import com.yashv.anime_guide.domain.repository.AnimeRepository
import com.yashv.anime_guide.utils.ApiResponse
import com.yashv.anime_guide.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeListUsecase @Inject constructor(private val repository: AnimeRepository) {
    suspend operator fun invoke(
        pageNum: Int = 1,
        pageSize: Int = Constants.PHOTOS_PER_PAGE
    ): Flow<ApiResponse<AnimeList>> = flow {
        emit(ApiResponse.Loading())
        val list = repository.loadAnimeList(pageNum, pageSize)
        if (list.isSuccessful) {
            emit(ApiResponse.Success(list.body()))
        } else {
            emit(ApiResponse.Error(list.errorBody().toString()))
        }
    }
}
