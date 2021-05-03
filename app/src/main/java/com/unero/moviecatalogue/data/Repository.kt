package com.unero.moviecatalogue.data

import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse
import retrofit2.Response

interface Repository {
    suspend fun topMovie(key: String): APIResponse<MovieResponse>
    suspend fun topTV(key: String): APIResponse<TVResponse>
    suspend fun genreMovie(key: String): APIResponse<GenreResponse>
    suspend fun genreTV(key: String): APIResponse<GenreResponse>

    // Test
    fun movieTest(key: String): Response<MovieResponse>
    fun tvTest(key: String): Response<TVResponse>
    fun genreTest(key: String): Response<GenreResponse>
}