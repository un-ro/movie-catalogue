package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.ui.detail.DetailViewModel
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import com.unero.moviecatalogue.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}