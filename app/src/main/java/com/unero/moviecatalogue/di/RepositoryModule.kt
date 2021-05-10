package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.RepositoryImpl
import com.unero.moviecatalogue.data.local.FavoriteDao
import com.unero.moviecatalogue.data.remote.Endpoint
import org.koin.dsl.module

val repositoryModule = module {
    fun repository(api: Endpoint, dao: FavoriteDao): Repository {
        return RepositoryImpl(api, dao)
    }
    single { repository(get(), get()) }
}