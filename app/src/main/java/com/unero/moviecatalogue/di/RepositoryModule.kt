package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.RepositoryImpl
import com.unero.moviecatalogue.data.remote.Endpoint
import org.koin.dsl.module

val repositoryModule = module {
    fun repository(api: Endpoint): Repository {
        return RepositoryImpl(api)
    }
    single { repository(get()) }
}