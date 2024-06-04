package com.yashv.anime_guide.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.yashv.anime_guide.R
import com.yashv.anime_guide.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    var loadingView: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingView = findViewById(R.id.loader)
        addRootFrag(AnimeListFragment(), AnimeListFragment.TAG)
    }

    fun showLoading() {
        loadingView?.isVisible = true
    }

    fun hideLoading() {
        loadingView?.isVisible = false
    }
}