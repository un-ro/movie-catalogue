package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.RepositoryImpl
import com.unero.moviecatalogue.data.local.LocalDataSource
import com.unero.moviecatalogue.data.remote.Endpoint
import org.koin.dsl.module

val repositoryModule = module {
    fun repository(api: Endpoint, localDataSource: LocalDataSource): Repository {
        return RepositoryImpl(api, localDataSource)
    }

    single { LocalDataSource(get()) }
    single { repository(get(), get()) }
}