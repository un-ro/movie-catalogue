package com.unero.moviecatalogue.di

import com.unero.moviecatalogue.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SharedViewModel(repository = get())
    }
}
