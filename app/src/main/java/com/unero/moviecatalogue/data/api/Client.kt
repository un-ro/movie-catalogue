package com.unero.moviecatalogue.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private const val baseUrl = "https://api.themoviedb.org/3/"

    val retrofit: Endpoint by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)

        retrofit
    }
}