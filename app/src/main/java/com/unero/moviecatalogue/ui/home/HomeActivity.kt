package com.unero.moviecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.databinding.ActivityHomeBinding
import com.unero.moviecatalogue.ui.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.apply {
            vp.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(binding.vp)
        }

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.item_favorite) {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            } else false
        }

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }
}