package com.unero.moviecatalogue.data.remote

import com.unero.moviecatalogue.data.remote.model.GenreResponse
import com.unero.moviecatalogue.data.remote.model.MovieResponse
import com.unero.moviecatalogue.data.remote.model.TVResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    // Popular
    @GET("movie/popular")
    suspend fun topMovie(
        @Query("api_key") key: String
    ): Response<MovieResponse>

    @GET("tv/popular")
    suspend fun topTV(
        @Query("api_key") key: String
    ): Response<TVResponse>

    // Genre
    @GET("genre/movie/list")
    suspend fun movieGenre(
        @Query("api_key") key: String
    ): Response<GenreResponse>

    @GET("genre/tv/list")
    suspend fun tvGenre(
        @Query("api_key") key: String
    ): Response<GenreResponse>

    // Testing
    @GET("movie/popular")
    fun testMovie(
        @Query("api_key") key: String
    ): Call<MovieResponse>

    @GET("tv/popular")
    fun testTV(
        @Query("api_key") key: String
    ): Call<TVResponse>
}