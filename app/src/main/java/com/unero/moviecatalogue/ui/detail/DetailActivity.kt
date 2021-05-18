package com.unero.moviecatalogue.ui.detail

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.databinding.ActivityDetailBinding
import com.unero.moviecatalogue.databinding.ItemChipBinding
import com.unero.moviecatalogue.util.Constant
import com.unero.moviecatalogue.util.Detail
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setLanguage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailViewModel>()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: Detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back Navigation
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Item Parcelable
        item = intent.getParcelableExtra("item")!!

        // UI
        setDataToUI()
        binding.tvOverview.movementMethod = ScrollingMovementMethod.getInstance()
        // Create Chip View
        setGenres()

        // Favorite
        setFABStatus()
        setFAB()
    }

    private fun setGenres() {
        viewModel.getGenres()
        when (item.type) {
            "movie" -> {
                viewModel.genreMovies.observe(this, {
                    addGenre(viewModel.parseGenre(item.genreIds, it))
                })
            }
            "tv" -> {
                viewModel.genreTV.observe(this, {
                    addGenre(viewModel.parseGenre(item.genreIds, it))
                })
            }
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

    private fun setDataToUI() {
        with(binding) {
            toolbar.title = item.title
            tvTitle.text = item.originalTitle
            tvMeta.text = resources.getString(R.string.meta,
                    setDateFormat(item.date),
                    setLanguage(item.language),
                    if (item.type == "movie") {
                        if (item.isAdult) "+18" else "Everyone"
                    } else {
                        if (item.country?.isNotEmpty() == true) item.country!![0] else "N/A"
                    }
            )
            rtbRate.rating = item.rate / 2
            tvOverview.text = item.overview

            toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.item_share) {
                    share()
                    true
                } else false
            }
        }
        Glide.with(this)
                .load(Constant.POSTER_URL + item.poster)
                .into(binding.ivBackground)
        Glide.with(this)
                .load(Constant.POSTER_URL + item.poster)
                .into(binding.ivPoster)
    }

    private fun share() {
        val shareText = getString(R.string.share, item.title, setDateFormat(item.date))

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    private fun setFAB(){
        // Observe status value, change drawable and database action
        viewModel.status.observe(this, {
            if (it) {
                binding.fabFav.apply {
                    setImageResource(R.drawable.ic_favorite_filled)
                    setOnClickListener {
                        viewModel.delete(item.toFavorite())
                        viewModel.status.value = false
                    }
                }
            } else {
                binding.fabFav.apply {
                    setImageResource(R.drawable.ic_favorite)
                    setOnClickListener {
                        viewModel.add(item.toFavorite())
                        viewModel.status.value = true
                    }
                }
            }
        })
    }

    private fun setFABStatus(){
        viewModel.searchFavorite(item.id).observe(this, {
            viewModel.status.value = it != null
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}