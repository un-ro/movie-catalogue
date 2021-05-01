package com.unero.moviecatalogue.ui.home.tabs.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.unero.moviecatalogue.databinding.FragmentMovieBinding
import com.unero.moviecatalogue.viewmodel.SharedViewModel

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: SharedViewModel by viewModels({ requireActivity() })
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        viewModel.movies.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                val movies = it.body()?.results
                if (movies != null) {
                    movieAdapter.setMovies(movies)
                    movieAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun setupRV() {
        with(binding.rvMovie) {
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }
}