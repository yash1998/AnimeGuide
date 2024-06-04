package com.yashv.anime_guide.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yashv.anime_guide.data.model.AnimeData
import com.yashv.anime_guide.databinding.ItemAnimeBriefBinding

class AnimeListAdapter(private val onItemClick: (String?) -> Unit) :
    ListAdapter<AnimeData?, AnimeListAdapter.AnimeListVH>(AnimeDataDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListVH {
        return AnimeListVH(
            ItemAnimeBriefBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeListVH, position: Int) {
        val anime = currentList.get(position)
        holder.bindClick(anime, onItemClick)
        holder.bind(anime)
    }

    inner class AnimeListVH(val binding: ItemAnimeBriefBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: AnimeData?) {
            anime ?: return
            binding.ivAnimeLogo.load(anime.image) {
                crossfade(true)
            }
            binding.tvAnimeTitle.text = anime.title
            binding.tvAnimeGenre.text = anime.genres?.joinToString()
        }

        fun bindClick(anime: AnimeData?, onItemClick: (String?) -> Unit) {
            anime ?: return
            binding.tvAnimeKnowMore.setOnClickListener {
                onItemClick(anime.id)
            }
        }
    }

    class AnimeDataDiffUtil : DiffUtil.ItemCallback<AnimeData?>() {
        override fun areItemsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem.id == newItem.id
        }
    }

}