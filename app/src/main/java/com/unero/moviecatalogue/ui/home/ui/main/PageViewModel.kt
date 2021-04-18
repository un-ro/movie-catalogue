package com.unero.moviecatalogue.ui.home.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.BuildConfig
import com.unero.moviecatalogue.data.api.Repository
import com.unero.moviecatalogue.data.model.Movie
import com.unero.moviecatalogue.data.model.TVShow
import kotlinx.coroutines.launch
import retrofit2.Response

class PageViewModel : ViewModel() {

    private val api_key = BuildConfig.KEY

    // Mutable Live Data
    private val _movies = MutableLiveData<Response<List<Movie>>>()
    private val _tv = MutableLiveData<Response<List<TVShow>>>()

    // Live Data
    val movies: LiveData<Response<List<Movie>>>
        get() = _movies

    val tv: LiveData<Response<List<TVShow>>>
        get() = _tv

    // Get
    fun topMovies() {
        viewModelScope.launch {
            try {
                _movies.value = Repository.topMovie(api_key)
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun topTV() {
        viewModelScope.launch {
            try {
                _tv.value = Repository.topTV(api_key)
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }
}