package com.yashv.anime_guide.utils

import android.util.Log
import com.yashv.anime_guide.BuildConfig

const val LOG_TAG = "AnimeGuideApp"
private val DEBUG = BuildConfig.DEBUG

fun logd(msg: String?) {
    if (DEBUG) Log.d(LOG_TAG, msg ?: "")
}

fun loge(msg: String?) {
    if (DEBUG) Log.e(LOG_TAG, msg ?: "")
}

fun logv(msg: String?) {
    if (DEBUG) Log.v(LOG_TAG, msg ?: "")
}

fun logi(msg: String?) {
    if (DEBUG) Log.i(LOG_TAG, msg ?: "")
}

fun logwtf(msg: String?) {
    if (DEBUG) Log.wtf(LOG_TAG, msg ?: "")
}