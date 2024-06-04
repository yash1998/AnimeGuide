package com.yashv.anime_guide.presentation.viewmodel

data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = ""
)