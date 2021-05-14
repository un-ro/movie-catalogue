package com.unero.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.unero.moviecatalogue.data.local.Favorite
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse

interface Repository {
    // Network
    suspend fun topMovie(): APIResponse<MovieResponse>
    suspend fun topTV(): APIResponse<TVResponse>
    suspend fun genreMovie(): APIResponse<GenreResponse>
    suspend fun genreTV(): APIResponse<GenreResponse>

    // Room
    suspend fun getAllFav(type: String): LiveData<List<Favorite>>
    fun searchFavorite(id: Int): LiveData<Favorite>
    suspend fun add(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
}