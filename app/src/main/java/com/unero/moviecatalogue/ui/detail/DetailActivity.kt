package com.unero.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import com.unero.moviecatalogue.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var item: Any? = null
    private val imageUrl = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = intent.getParcelableExtra("item")

        setDataToUI(item)
    }

    private fun setDataToUI(item: Any?) {
        if (item is Movie) {
            with(binding) {
                tvTitle.text = item.title
                tvRating.text = item.vote_average.toString()
                tvReleaseDate.text = item.release_date
                tvOverview.text = item.overview
            }
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(DrawableImageViewTarget(binding.ivBackground))
        } else {
            item as TVShow
            with(binding) {
                tvTitle.text = item.original_name
                tvRating.text = item.vote_average.toString()
                tvReleaseDate.text = item.first_air_date
                tvOverview.text = item.overview
            }
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(DrawableImageViewTarget(binding.ivBackground))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}