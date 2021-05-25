package com.unero.moviecatalogue.ui.favorite.tab.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unero.moviecatalogue.R
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.databinding.ItemBinding
import com.unero.moviecatalogue.ui.detail.DetailActivity
import com.unero.moviecatalogue.ui.favorite.tab.tv.TVFavoriteAdapter.TVFavoriteViewHolder
import com.unero.moviecatalogue.util.Constant
import com.unero.moviecatalogue.util.Formatter.setDateFormat
import com.unero.moviecatalogue.util.Formatter.setTitle

class TVFavoriteAdapter: PagedListAdapter<Favorite, TVFavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVFavoriteViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVFavoriteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class TVFavoriteViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            binding.apply {
                tvTitle.text = setTitle(favorite.originalTitle)
                tvRelease.text = setDateFormat(favorite.date)

                Glide.with(binding.root)
                    .load(Constant.POSTER_URL + favorite.poster)
                    .placeholder(R.drawable.placeholder_poster)
                    .into(ivPoster)

                root.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("item", favorite.toDetail())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }

        }
    }
}