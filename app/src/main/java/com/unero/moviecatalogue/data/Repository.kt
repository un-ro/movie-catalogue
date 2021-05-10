package com.unero.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.unero.moviecatalogue.data.local.FavoriteEntity
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse

interface Repository {
    // Network
    suspend fun topMovie(key: String): APIResponse<MovieResponse>
    suspend fun topTV(key: String): APIResponse<TVResponse>
    suspend fun genreMovie(key: String): APIResponse<GenreResponse>
    suspend fun genreTV(key: String): APIResponse<GenreResponse>

    // Room
    suspend fun getAllFav(type: String): LiveData<List<FavoriteEntity>>
}