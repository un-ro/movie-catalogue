package com.unero.moviecatalogue.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.unero.moviecatalogue.databinding.ActivityHomeBinding
import com.unero.moviecatalogue.viewmodel.SharedViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.apply {
            vp.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(binding.vp)
        }

        supportActionBar?.elevation = 0f

        setupData()

        viewModel.errorMsg.observe(this, {
            noConnection(it)
        })

        binding.btnRefresh.setOnClickListener {
            setupData()
            setViewPager()
        }
    }

    private fun noConnection(error: String) {
        binding.apply {
            vp.visibility = View.GONE
            btnRefresh.visibility = View.VISIBLE
            Snackbar.make(root, error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setViewPager(){
        binding.vp.visibility = View.VISIBLE
        binding.btnRefresh.visibility = View.GONE
    }

    private fun setupData() {
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Fetch from API
        viewModel.topMovies()
        viewModel.topTV()
    }
}