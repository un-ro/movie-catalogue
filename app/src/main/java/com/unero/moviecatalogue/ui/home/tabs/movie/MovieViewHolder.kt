package com.unero.moviecatalogue.ui.home.tabs.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.databinding.ItemMovieBinding

class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    private val image_url = "https://image.tmdb.org/t/p/w500"

    fun bind(movie: Movie) {
        binding.apply {
            tvTitle.text = movie.title
            tvRating.text = movie.vote_average.toString()
            tvRelease.text = movie.release_date

            Glide.with(binding.root)
                    .load(image_url + movie.poster_path)
                    .into(ivPoster)
        }
    }
}