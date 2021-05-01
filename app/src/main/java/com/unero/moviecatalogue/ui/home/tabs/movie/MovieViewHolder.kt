package com.unero.moviecatalogue.ui.home.tabs.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setTitle

class MovieViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val imageUrl = "https://image.tmdb.org/t/p/w500"

    fun bind(movie: Movie) {
        binding.apply {
            tvTitle.text = setTitle(movie.title)
            tvRelease.text = setDateFormat(movie.date)

            Glide.with(binding.root)
                    .load(imageUrl + movie.poster)
                    .into(ivPoster)

            root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("item", movie)
                itemView.context.startActivity(intent)
            }
        }
    }
}