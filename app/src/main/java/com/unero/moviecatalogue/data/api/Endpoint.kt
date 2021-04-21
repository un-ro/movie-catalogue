package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.ResultMovie
import com.unero.moviecatalogue.data.model.ResultShows
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/popular")
    suspend fun topMovie(
        @Query("api_key") key: String
    ): Response<ResultMovie>

    @GET("tv/popular")
    suspend fun topTV(
        @Query("api_key") key: String
    ): Response<ResultShows>

    // Testing
    @GET("movie/popular")
    fun testMovie(
        @Query("api_key") key: String
    ): Call<ResultMovie>

    @GET("tv/popular")
    fun testTV(
        @Query("api_key") key: String
    ): Call<ResultShows>
}