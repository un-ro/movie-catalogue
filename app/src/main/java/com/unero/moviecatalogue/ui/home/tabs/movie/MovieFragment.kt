package com.unero.moviecatalogue.ui.home.tabs.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.unero.moviecatalogue.databinding.FragmentMovieBinding
import com.unero.moviecatalogue.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModel<SharedViewModel>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.topMovies()
    }

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
            if (it.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                movieAdapter.setMovies(it)
                movieAdapter.notifyDataSetChanged()
            } else {
                showMessage("Unknown Error")
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, {
            showMessage(it)
        })
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupRV() {
        with(binding.rvMovie) {
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }
}