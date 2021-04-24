package com.unero.moviecatalogue.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import com.unero.moviecatalogue.databinding.ActivityDetailBinding
import com.unero.moviecatalogue.util.Detail

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var item: Any? = null
    private val imageUrl = "https://image.tmdb.org/t/p/w500"
    private val detail =  Detail()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        item = intent.getParcelableExtra("item")

        setDataToUI(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setDataToUI(item: Any?) {
        if (item is Movie) {
            with(binding) {
                toolbar.title = "Movie"
                tvTitle.text = item.title

                tvMeta.text = resources.getString(R.string.meta,
                    detail.setDateFormat(item.date),
                    detail.setLanguage(item.language),
                    if (item.isAdult) "+18" else "Everyone"
                )

                rtbRate.rating = item.rate / 2

                tvOverview.text = item.overview

                toolbar.setOnMenuItemClickListener {
                    if (it.itemId == R.id.item_share) {
                        share(item)
                        true
                    } else false
                }
            }
            Glide.with(this)
                .load(imageUrl + item.poster)
                .into(binding.ivBackground)
            Glide.with(this)
                .load(imageUrl + item.poster)
                .into(binding.ivPoster)
        } else if (item is TVShow) {
            with(binding) {
                toolbar.title = "TV Show"
                tvTitle.text = item.title

                tvMeta.text = resources.getString(R.string.meta,
                    detail.setDateFormat(item.date),
                    detail.setLanguage(item.language),
                    if (item.country.isNotEmpty()) item.country[0] else "N/A"
                )

                rtbRate.rating = item.rate / 2

                tvOverview.text = item.overview

                toolbar.setOnMenuItemClickListener {
                    if (it.itemId == R.id.item_share) {
                        share(item)
                        true
                    } else false
                }
            }
            Glide.with(this)
                .load(imageUrl + item.poster)
                .into(binding.ivBackground)
            Glide.with(this)
                .load(imageUrl + item.poster)
                .into(binding.ivPoster)
        }
    }

    private fun share(item: Any) {
        var shareText = ""
        if (item is Movie) {
            shareText = getString(R.string.share, item.title, detail.setDateFormat(item.date))
        } else if (item is TVShow) {
            shareText = getString(R.string.share, item.title, detail.setDateFormat(item.date))
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
}