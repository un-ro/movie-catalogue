package com.unero.moviecatalogue.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import com.unero.moviecatalogue.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var item: Any? = null
    private val imageUrl = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        item = intent.getParcelableExtra("item")

        setDataToUI(item)
    }

    private fun setDataToUI(item: Any?) {
        if (item is Movie) {
            with(binding) {
                toolbar.title = "Movie"
                tvTitle.text = item.title

                tvMeta.text = resources.getString(R.string.meta,
                    setDateFormat(item.release_date),
                    item.original_language,
                    if (item.adult) "+18" else "Everyone"
                )

                rtbRate.rating = item.vote_average / 2

                tvOverview.text = item.overview

                toolbar.setOnMenuItemClickListener {
                    if (it.itemId == R.id.item_share) {
                        share(item)
                        true
                    } else false
                }
            }
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(binding.ivBackground)
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(binding.ivPoster)
        } else if (item is TVShow) {
            with(binding) {
                toolbar.title = "TV Show"
                tvTitle.text = item.original_name

                tvMeta.text = resources.getString(R.string.meta,
                    setDateFormat(item.first_air_date),
                    item.original_language,
                    if (item.origin_country.isNotEmpty()) item.origin_country[0] else "N/A"
                )

                rtbRate.rating = item.vote_average / 2

                tvOverview.text = item.overview

                toolbar.setOnMenuItemClickListener {
                    if (it.itemId == R.id.item_share) {
                        share(item)
                        true
                    } else false
                }
            }
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(binding.ivBackground)
            Glide.with(this)
                .load(imageUrl + item.poster_path)
                .into(binding.ivPoster)
        }
    }

    private fun share(item: Any) {
        var shareText = ""
        if (item is Movie) {
            shareText = getString(R.string.share, item.title, setDateFormat(item.release_date))
        } else if (item is TVShow) {
            shareText = getString(R.string.share, item.original_name, setDateFormat(item.first_air_date))
        }

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    // Change yyyy-MM-dd -> dd MMMM yyyy
    private fun setDateFormat(oldDate: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val newDate = oldFormat.parse(oldDate)
        return newFormat.format(newDate ?: oldDate).toString()
    }
}