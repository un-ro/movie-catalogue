package com.unero.moviecatalogue.ui.home.tabs.tv

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.data.model.TVShow
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import java.text.SimpleDateFormat
import java.util.*

class TVViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageUrl = "https://image.tmdb.org/t/p/w500"

    fun bind(tvShow: TVShow) {
        binding.apply {
            tvTitle.text = tvShow.original_name
            tvRating.text = tvShow.vote_average.toString()
            tvRelease.text = setDateFormat(tvShow.first_air_date)

            Glide.with(binding.root)
                    .load(imageUrl + tvShow.poster_path)
                    .into(ivPoster)

            setRateColor(tvShow.vote_average)

            root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("item", tvShow)
                itemView.context.startActivity(intent)
            }
        }
    }

    // Change yyyy-MM-dd -> dd MMMM yyyy
    private fun setDateFormat(oldDate: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val newDate = oldFormat.parse(oldDate)
        return newFormat.format(newDate ?: oldDate).toString()
    }

    // Set Color based on Rating
    private fun setRateColor(score: Float) {
        if (score >= 8.0)
            binding.ivCircle.setColorFilter(
                    Color.parseColor("#4CAF50"),
                    PorterDuff.Mode.SRC_IN) // Popular
        else if (score >= 6 && score < 8)
            binding.ivCircle.setColorFilter(
                    Color.parseColor("#FF5722"),
                    PorterDuff.Mode.SRC_IN) // Decent
        else
            binding.ivCircle.setColorFilter(
                    Color.parseColor("#FF1744"),
                    PorterDuff.Mode.SRC_IN) // Not Popular
    }
}