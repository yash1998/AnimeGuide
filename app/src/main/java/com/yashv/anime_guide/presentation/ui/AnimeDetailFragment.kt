package com.yashv.anime_guide.presentation.ui

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.yashv.anime_guide.R
import com.yashv.anime_guide.base.BaseFragment
import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.databinding.FragmentAnimeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailFragment : BaseFragment<FragmentAnimeDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAnimeDetailBinding
        get() = FragmentAnimeDetailBinding::inflate

    private var data: AnimeData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchArgs()
        setupViews()
    }

    private fun fetchArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data = arguments?.getParcelable(ANIME_DATA, AnimeData::class.java)
        } else {
            data = arguments?.getParcelable(ANIME_DATA) as? AnimeData
        }
    }

    private fun setupViews() {
        bi.ivAnimeBanner.load(data?.image)
        bi.tvAnimeTitle.text = data?.title
        bi.tvAnimeGenre.text = getString(R.string.genres_x).format(data?.genres?.joinToString())
        bi.tvAnimeDescription.text = getString(R.string.description_x).format(data?.synopsis)
        bi.tvAnimeDescription.movementMethod = ScrollingMovementMethod()
    }

    companion object {
        const val TAG = "AnimeDetailFragment"
        private const val ANIME_DATA = "ANIME_DATA"

        fun newInstance(data: AnimeData?) = AnimeDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ANIME_DATA, data)
            }
        }
    }

}