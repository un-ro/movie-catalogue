package com.unero.moviecatalogue.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private const val baseUrl = "https://api.themoviedb.org/3/"

    val retrofit: Endpoint by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)

        retrofit
    }

//    val endpoint: Endpoint by lazy {
//        retrofit.create(Endpoint::class.java)
//    }
}