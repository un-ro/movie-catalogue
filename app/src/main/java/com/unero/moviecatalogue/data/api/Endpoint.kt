package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.MovieResponse
import com.unero.moviecatalogue.data.model.TVResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/popular")
    suspend fun topMovie(
        @Query("api_key") key: String
    ): Response<MovieResponse>

    @GET("tv/popular")
    suspend fun topTV(
        @Query("api_key") key: String
    ): Response<TVResponse>

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