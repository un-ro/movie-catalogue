package com.unero.moviecatalogue.data

import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import retrofit2.Response

interface Repository {
    suspend fun topMovie(key: String): Response<MovieResponse>
    suspend fun topTV(key: String): Response<TVResponse>
    suspend fun genreMovie(key: String): Response<GenreResponse>
    suspend fun genreTV(key: String): Response<GenreResponse>
}