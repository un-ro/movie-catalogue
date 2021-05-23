package com.unero.moviecatalogue.ui.home.tabs.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.databinding.ItemBinding

class TVAdapter: RecyclerView.Adapter<TVViewHolder>() {

    private val shows = mutableListOf<TVShow>()
    private val favorites = mutableListOf<Favorite>()

    fun setShows(newShows: List<TVShow>) {
        shows.clear()
        shows.addAll(newShows)
        notifyDataSetChanged()
    }

    fun setFavorites(newFavorite: List<Favorite>) {
        this.shows.clear()
        this.favorites.clear()
        this.favorites.addAll(newFavorite)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        if (shows.isEmpty()) {
            holder.bindFavorite(favorites[position])
        } else {
            holder.bind(shows[position])
        }
    }

    override fun getItemCount(): Int {
        return if(shows.isEmpty()) {
            favorites.size
        } else {
            shows.size
        }
    }
}