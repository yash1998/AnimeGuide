package com.yashv.anime_guide.presentation.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.yashv.anime_guide.base.BaseActivity
import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.presentation.ui.AnimeDetailFragment
import com.yashv.anime_guide.presentation.ui.MainActivity
import com.yashv.anime_guide.presentation.ui.addFrag
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainUiViewModel @Inject constructor(): ViewModel() {

    fun openAnimeDetailScreen(activity: FragmentActivity?, data: AnimeData?) {
        if (activity == null || activity.isFinishing) return
        (activity as? BaseActivity)?.addFrag(
            AnimeDetailFragment.newInstance(data),
            AnimeDetailFragment.TAG
        )
    }

    fun showLoading(activity: FragmentActivity?) {
        if (activity == null || activity.isFinishing) return
        (activity as? MainActivity)?.showLoading()
    }

    fun hideLoading(activity: FragmentActivity?) {
        if (activity == null || activity.isFinishing) return
        (activity as? MainActivity)?.hideLoading()
    }
}