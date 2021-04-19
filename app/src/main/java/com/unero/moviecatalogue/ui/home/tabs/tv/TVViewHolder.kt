package com.unero.moviecatalogue.ui.home.tabs.tv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.data.model.TVShow
import com.unero.moviecatalogue.databinding.ItemTvBinding

class TVViewHolder(private val binding: ItemTvBinding) : RecyclerView.ViewHolder(binding.root) {

    private val image_url = "https://image.tmdb.org/t/p/w500"

    fun bind(tvShow: TVShow) {
        binding.apply {
            tvTitle.text = tvShow.original_name
            tvRating.text = tvShow.vote_average.toString()
            tvRelease.text = tvShow.first_air_date

            Glide.with(binding.root)
                    .load(image_url + tvShow.poster_path)
                    .into(ivPoster)
        }
    }
}