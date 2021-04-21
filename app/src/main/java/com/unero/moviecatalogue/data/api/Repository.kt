package com.unero.moviecatalogue.data.api

import com.unero.moviecatalogue.data.model.ResultMovie
import com.unero.moviecatalogue.data.model.ResultShows
import retrofit2.Call
import retrofit2.Response

object Repository {

    suspend fun topMovie(key: String): Response<ResultMovie> {
        return Client.retrofit.topMovie(key)
    }

    suspend fun topTV(key: String): Response<ResultShows> {
        return Client.retrofit.topTV(key)
    }

    // Testing
    fun testMovie(key: String): Call<ResultMovie> {
        return Client.retrofit.testMovie(key)
    }

    fun testTV(key: String): Call<ResultShows> {
        return Client.retrofit.testTV(key)
    }
}