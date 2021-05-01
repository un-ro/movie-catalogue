package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.MovieResponse
import com.unero.moviecatalogue.data.model.TVResponse
import retrofit2.Call
import retrofit2.Response

object Repository {

    suspend fun topMovie(key: String): Response<MovieResponse> {
        return Client.retrofit.topMovie(key)
    }

    suspend fun topTV(key: String): Response<TVResponse> {
        return Client.retrofit.topTV(key)
    }

    // Testing
    fun testMovie(key: String): Call<MovieResponse> {
        return Client.retrofit.testMovie(key)
    }

    fun testTV(key: String): Call<TVResponse> {
        return Client.retrofit.testTV(key)
    }
}