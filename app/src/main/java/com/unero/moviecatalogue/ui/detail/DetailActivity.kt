package com.unero.moviecatalogue.ui.detail

import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.remote.model.Movie
import com.unero.moviecatalogue.data.remote.model.TVShow
import com.unero.moviecatalogue.databinding.ActivityDetailBinding
import com.unero.moviecatalogue.databinding.ItemChipBinding
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setLanguage
import com.unero.moviecatalogue.viewmodel.SharedViewModel

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvOverview.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        coba()
    }

    private fun coba() {
        val vm = ViewModelProvider(this)[SharedViewModel::class.java]
        vm.getGenres()

        if (item is Movie) {
            vm.genreMovies.observe(this, {
                if (it.isSuccessful) {
                    val list = it.body()?.genres
                    (item as? Movie)?.genreIds?.forEach { movie ->
                        list?.forEach { genre ->
                            if (movie == genre.id) {
                                addGenre(genre.name)
                            }
                        }
                    }
                }
            })
        } else if (item is TVShow) {
            vm.genreTV.observe(this, {
                if (it.isSuccessful) {
                    val list = it.body()?.genres
                    (item as TVShow).genreIds.forEach { movie ->
                        list?.forEach { genre ->
                            if (movie == genre.id) {
                                addGenre(genre.name)
                            }
                        }
                    }
                }
            })
        }
    }

    private fun addGenre(name: String) {
        val itemChip = ItemChipBinding.inflate(layoutInflater)
        itemChip.chip1.text = name
        binding.cgGenres.addView(itemChip.chip1)
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