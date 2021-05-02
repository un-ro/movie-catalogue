package com.unero.moviecatalogue.ui.home.tabs.tv

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setTitle

class TVViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageUrl = itemView.resources.getString(R.string.imageUrl)

    fun bind(tvShow: TVShow) {
        binding.apply {
            tvTitle.text = setTitle(tvShow.title)
            tvRelease.text = setDateFormat(tvShow.date)

            Glide.with(binding.root)
                    .load(imageUrl + tvShow.poster)
                    .into(ivPoster)

            root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("item", tvShow)
                itemView.context.startActivity(intent)
            }
        }
    }
}