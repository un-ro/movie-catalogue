package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.util.Constant.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun client(): Endpoint {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }
    single { client() }
}