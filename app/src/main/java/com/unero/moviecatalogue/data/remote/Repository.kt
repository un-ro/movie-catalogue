package com.unero.moviecatalogue.data.remote

import com.unero.moviecatalogue.data.remote.model.GenreResponse
import com.unero.moviecatalogue.data.remote.model.MovieResponse
import com.unero.moviecatalogue.data.remote.model.TVResponse
import retrofit2.Call
import retrofit2.Response

object Repository {

    suspend fun topMovie(key: String): Response<MovieResponse> {
        return Client.retrofit.topMovie(key)
    }

    suspend fun genreMovie(key: String): Response<GenreResponse> {
        return Client.retrofit.movieGenre(key)
    }

    suspend fun topTV(key: String): Response<TVResponse> {
        return Client.retrofit.topTV(key)
    }

    suspend fun genreTV(key: String): Response<GenreResponse> {
        return Client.retrofit.tvGenre(key)
    }


    // Testing
    fun testMovie(key: String): Call<MovieResponse> {
        return Client.retrofit.testMovie(key)
    }

    fun testTV(key: String): Call<TVResponse> {
        return Client.retrofit.testTV(key)
    }
}