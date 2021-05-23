package com.unero.moviecatalogue.ui.home.tabs.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.databinding.ItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()
    private val favorites = mutableListOf<Favorite>()

    fun setMovies(newMovies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun setFavorite(newFavorites: List<Favorite>) {
        this.movies.clear()
        this.favorites.clear()
        this.favorites.addAll(newFavorites)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movies.isEmpty()) {
            holder.bindFavorite(favorites[position])
        } else {
            holder.bind(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return if (movies.isEmpty()) {
            favorites.size
        } else {
            movies.size
        }
    }
}