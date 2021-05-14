package com.unero.moviecatalogue.ui.home.tabs.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import com.unero.moviecatalogue.util.Constant.POSTER_URL
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setTitle

class MovieViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        movie.type = "movie"
        binding.apply {
            tvTitle.text = setTitle(movie.originalTitle)
            tvRelease.text = setDateFormat(movie.date)

            Glide.with(binding.root)
                    .load(POSTER_URL + movie.poster)
                    .placeholder(R.drawable.placeholder_poster)
                    .into(ivPoster)

            root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("item", movie)
                itemView.context.startActivity(intent)
            }
        }
    }
}