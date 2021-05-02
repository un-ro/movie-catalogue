package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.data.remote.Endpoint
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.themoviedb.org/3/"

val apiModule = module {
    fun client(): Endpoint {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }
    single { client() }
}