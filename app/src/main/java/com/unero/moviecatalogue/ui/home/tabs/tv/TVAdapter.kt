package com.unero.moviecatalogue.ui.home.tabs.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unero.moviecatalogue.data.remote.model.TVShow
import com.unero.moviecatalogue.databinding.ItemBinding

class TVAdapter: RecyclerView.Adapter<TVViewHolder>() {

    private val shows = mutableListOf<TVShow>()

    fun setShows(newShows: List<TVShow>) {
        shows.clear()
        shows.addAll(newShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount(): Int = shows.size
}