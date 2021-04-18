package com.unero.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unero.moviecatalogue.databinding.ActivityHomeBinding
import com.unero.moviecatalogue.ui.home.ui.main.SectionsPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(binding.viewPager)
        }

        supportActionBar?.elevation = 0f
    }
}