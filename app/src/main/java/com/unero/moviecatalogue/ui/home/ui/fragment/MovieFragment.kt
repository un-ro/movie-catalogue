package com.unero.moviecatalogue.ui.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.unero.moviecatalogue.databinding.FragmentMovieBinding
import com.unero.moviecatalogue.ui.home.ui.main.PageViewModel

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PageViewModel::class.java)

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

        viewModel.movies.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                println(it.body())
            }
        })
    }
}