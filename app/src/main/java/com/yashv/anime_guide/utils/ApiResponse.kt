package com.yashv.anime_guide.utils

sealed class ApiResponse<T>(val data: T?, val msg: String? = null) {
    class Loading<T> : ApiResponse<T>(null)
    class Success<T>(data: T?) : ApiResponse<T>(data)
    class Error<T>(msg: String?) : ApiResponse<T>(null, msg)
}