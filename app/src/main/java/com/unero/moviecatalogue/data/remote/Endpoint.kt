package com.unero.moviecatalogue.data.remote

import com.unero.moviecatalogue.BuildConfig.KEY
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {
    // Popular
    @GET("movie/popular?api_key=$KEY")
    suspend fun topMovie(): Response<MovieResponse>

    @GET("tv/popular?api_key=$KEY")
    suspend fun topTV(): Response<TVResponse>

    // Genre
    @GET("genre/movie/list?api_key=$KEY")
    suspend fun movieGenre(): Response<GenreResponse>

    @GET("genre/tv/list?api_key=$KEY")
    suspend fun tvGenre(): Response<GenreResponse>
}