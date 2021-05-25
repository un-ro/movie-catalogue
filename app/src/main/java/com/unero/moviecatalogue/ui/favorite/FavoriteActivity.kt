package com.unero.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.unero.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = pagerAdapter
            tabs.setupWithViewPager(binding.viewPager)
        }

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}