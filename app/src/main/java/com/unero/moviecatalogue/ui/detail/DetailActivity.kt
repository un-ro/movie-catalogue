package com.unero.moviecatalogue.ui.detail

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.databinding.ActivityDetailBinding
import com.unero.moviecatalogue.databinding.ItemChipBinding
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setLanguage
import com.unero.moviecatalogue.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<SharedViewModel>()
    private lateinit var binding: ActivityDetailBinding
    private var item: Any? = null
    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUrl = binding.root.resources.getString(R.string.imageUrl)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Receive data from parcel
        item = intent.getParcelableExtra("item")

        setDataToUI(item)

        binding.tvOverview.movementMethod = ScrollingMovementMethod.getInstance()

        // Create Chip View
        setGenres()
    }

    private fun setGenres() {
        viewModel.getGenres()

        if (item is Movie) {
            viewModel.genreMovies.observe(this, {
                addGenre(viewModel.parseGenre((item as Movie).genreIds, it))
            })
        } else if (item is TVShow) {
            viewModel.genreTV.observe(this, {
                addGenre(viewModel.parseGenre((item as TVShow).genreIds, it))
            })
        }
    }

    private fun addGenre(name: List<String>) {
        name.forEach {
            val itemChip = ItemChipBinding.inflate(layoutInflater)
            itemChip.chip1.text = it
            binding.cgGenres.addView(itemChip.chip1)
        }
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
                    setDateFormat(item.date),
                    setLanguage(item.language),
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
                    setDateFormat(item.date),
                    setLanguage(item.language),
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
            shareText = getString(R.string.share, item.title, setDateFormat(item.date))
        } else if (item is TVShow) {
            shareText = getString(R.string.share, item.title, setDateFormat(item.date))
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
