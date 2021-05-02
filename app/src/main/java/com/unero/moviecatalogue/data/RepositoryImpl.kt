package com.unero.moviecatalogue.data

import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import retrofit2.Response

class RepositoryImpl( private val api: Endpoint): Repository {
    override suspend fun topMovie(key: String): Response<MovieResponse> {
        return api.topMovie(key)
    }

    override suspend fun topTV(key: String): Response<TVResponse> {
        return api.topTV(key)
    }

    override suspend fun genreMovie(key: String): Response<GenreResponse> {
        return api.movieGenre(key)
    }

    override suspend fun genreTV(key: String): Response<GenreResponse> {
        return api.tvGenre(key)
    }
}