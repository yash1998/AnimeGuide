package com.yashv.anime_guide.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yashv.anime_guide.base.BaseActivity
import com.yashv.anime_guide.base.BaseFragment
import com.yashv.anime_guide.databinding.FragmentAnimeListBinding
import com.yashv.anime_guide.presentation.adapters.AnimeListAdapter
import com.yashv.anime_guide.presentation.viewmodel.AnimeListViewModel
import com.yashv.anime_guide.presentation.viewmodel.MainUiViewModel
import com.yashv.anime_guide.utils.isNotNull
import com.yashv.anime_guide.utils.logd
import com.yashv.anime_guide.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeListFragment : BaseFragment<FragmentAnimeListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAnimeListBinding
        get() = FragmentAnimeListBinding::inflate

    private val animeListVM: AnimeListViewModel by activityViewModels()
    private val mainUiVM: MainUiViewModel by activityViewModels()
    private val adapter by lazy {
        AnimeListAdapter { id -> getAnimeDetailById(id) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        getAnimeList()  // First time
    }

    private fun setupViews() {
        bi.rvAnimeList.adapter = adapter
        bi.tvAnimeListScreenTitle.setOnClickListener {
            getAnimeList()
        }
    }

    private fun getAnimeList() {
        animeListVM.getAnimeList()
    }

    private fun getAnimeDetailById(id: String?) {
        id?.let {
            animeListVM.getAnimeDetailById(id)
        } ?: run {
            logd("Anime id is null")
            context?.showToast("Anime id is null")
        }
    }

    private fun setupObservers() {
        animeListVM.animeListDataState.observe(viewLifecycleOwner) {
            mainUiVM.hideLoading(activity)
            if (it.isLoading) {
                mainUiVM.showLoading(activity)
            } else if (it.data?.animeData.isNullOrEmpty().not()) {
                adapter.submitList(it.data?.animeData)
            } else if (it.error.isNullOrEmpty().not()) {
                context?.showToast(it.error)
            } else {
                logd(it.error)
                logd("Something went wrong...")
            }
        }

        animeListVM.animeDetailDataState.observe(viewLifecycleOwner) {
            mainUiVM.hideLoading(activity)
            if (it.isLoading) {
                mainUiVM.showLoading(activity)
            } else if (it.data.isNotNull()) {
                mainUiVM.openAnimeDetailScreen(activity, it.data)
            } else if (it.error.isNullOrEmpty().not()) {
                context?.showToast(it.error)
            } else {
                logd("Something went wrong...")
            }
            animeListVM.clearAnimeDetailResults()
        }
    }

    companion object {
        const val TAG = "AnimeListFragment"
    }
}