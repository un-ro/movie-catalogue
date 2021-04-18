package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/popular")
    suspend fun topMovie(
        @Query("api_key") key: String
    ): Response<List<Movie>>

    @GET("tv/popular")
    suspend fun topTV(
        @Query("api_key") key: String
    ): Response<List<TVShow>>
}