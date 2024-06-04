package com.yashv.anime_guide.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.data.model.AnimeList
import com.yashv.anime_guide.domain.usecase.GetAnimeDetailByIdUsecase
import com.yashv.anime_guide.domain.usecase.GetAnimeListUsecase
import com.yashv.anime_guide.utils.ApiResponse
import com.yashv.anime_guide.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUsecase: GetAnimeListUsecase,
    private val getAnimeDetailByIdUsecase: GetAnimeDetailByIdUsecase
) : ViewModel() {

    private var _animeList = MutableLiveData(DataState<AnimeList>())
    val animeListDataState: LiveData<DataState<AnimeList>>
        get() = _animeList

    private var _animeDetail = MutableLiveData(DataState<AnimeData>())
    val animeDetailDataState: LiveData<DataState<AnimeData>>
        get() = _animeDetail

    private var currentPage = 1

    fun getAnimeList() = viewModelScope.launch(Dispatchers.IO) {
        _animeList.postValue(DataState(isLoading = true))
        val apiResponse = getAnimeListUsecase(currentPage, Constants.PHOTOS_PER_PAGE)
        apiResponse.collectLatest {
            when (it) {
                is ApiResponse.Success -> {
                    _animeList.postValue(DataState(data = it.data))
                    currentPage++
                }
                is ApiResponse.Loading -> {
                    _animeList.postValue(DataState(isLoading = true))
                }
                is ApiResponse.Error -> {
                    _animeList.postValue(DataState(error = it.msg))
                }
            }
        }
    }

    fun getAnimeDetailById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _animeList.postValue(DataState(isLoading = true))
        val apiResponse = getAnimeDetailByIdUsecase(id)
        apiResponse.collectLatest {
            when (it) {
                is ApiResponse.Success -> {
                    _animeDetail.postValue(DataState(data = it.data))
                }
                is ApiResponse.Loading -> {
                    _animeDetail.postValue(DataState(isLoading = true))
                }
                is ApiResponse.Error -> {
                    _animeDetail.postValue(DataState(error = it.msg))
                }
            }
        }
    }

    fun clearAnimeDetailResults() {
        _animeDetail.postValue(DataState())
    }

}