package com.unero.moviecatalogue.ui.favorite.tab.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unero.moviecatalogue.databinding.FragmentMovieBinding
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var movieAdapter: MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        viewModel.getFav("movie").observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                showMessage(true)
                viewModel.setLoad(false)
            } else {
                movieAdapter.submitList(it)
                showMessage(false)
                viewModel.setLoad(false)
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

    private fun showMessage(boolean: Boolean) {
        if (boolean) {
            binding.apply {
                iconError.visibility = View.VISIBLE
                lblEmptyFavorite.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                iconError.visibility = View.GONE
                lblEmptyFavorite.visibility = View.GONE
            }
        }
    }

    private fun setupRV() {
        with(binding.rvMovie) {
            movieAdapter = MovieFavoriteAdapter()
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }
}