package com.unero.moviecatalogue.ui.home.tabs.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import java.text.SimpleDateFormat
import java.util.*

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

    private fun setTitle(title: String): String {
        return if (title.length >= 25) {
            StringBuilder()
                    .append(title.take(25))
                    .append("...")
                    .toString()
        } else {
            title
        }
    }

    // Change yyyy-MM-dd -> dd MMMM yyyy
    private fun setDateFormat(oldDate: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val newDate = oldFormat.parse(oldDate)
        return newFormat.format(newDate ?: oldDate).toString()
    }
}