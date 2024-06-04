package com.yashv.anime_guide.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Any?.isNull() = this == null

fun Any?.isNotNull() = this != null