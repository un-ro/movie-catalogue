package com.unero.moviecatalogue.ui.home.tabs.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.databinding.ItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun setMovies(newMovies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
