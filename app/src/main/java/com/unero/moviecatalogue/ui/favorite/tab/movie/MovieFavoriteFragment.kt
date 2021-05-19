package com.unero.moviecatalogue.ui.favorite.tab.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.unero.moviecatalogue.databinding.FragmentMovieBinding
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import com.unero.moviecatalogue.ui.home.tabs.movie.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFav("movie")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        viewModel.favorites.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                movieAdapter.setFavorite(it)
                movieAdapter.notifyDataSetChanged()
            } else {
                showMessage()
            }
        })
    }

    private fun showMessage() {
        Snackbar.make(binding.root, "Unknown Error", Snackbar.LENGTH_LONG).show()
    }

    private fun setupRV() {
        with(binding.rvMovie) {
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }
}