package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import retrofit2.Response

object Repository {

    suspend fun topMovie(key: String): Response<List<Movie>> {
        return Client.retrofit.topMovie(key)
    }

    suspend fun topTV(key: String): Response<List<TVShow>> {
        return Client.retrofit.topTV(key)
    }
}