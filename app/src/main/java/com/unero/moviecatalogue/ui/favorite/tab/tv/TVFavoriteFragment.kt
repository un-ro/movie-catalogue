package com.unero.moviecatalogue.ui.favorite.tab.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unero.moviecatalogue.databinding.FragmentTVShowBinding
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import com.unero.moviecatalogue.ui.home.tabs.tv.TVAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTVShowBinding
    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var tvAdapter: TVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFav("tv")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        viewModel.favorites.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                tvAdapter.setFavorites(it)
                tvAdapter.notifyDataSetChanged()
            } else {
                showMessage()
            }
        })

        viewModel.showLoading.observe(viewLifecycleOwner, {
            binding.shimmerFrameLayout.apply {
                if (!it) {
                    stopShimmer()
                    visibility = View.GONE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    private fun showMessage() {
        binding.apply {
            iconError.visibility = View.VISIBLE
            lblEmptyFavorite.visibility = View.VISIBLE
        }
    }

    private fun setupRV() {
        with(binding.rvTv) {
            tvAdapter = TVAdapter()
            adapter = tvAdapter
            setHasFixedSize(true)
        }
    }
}