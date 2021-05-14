package com.unero.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.local.Favorite
import com.unero.moviecatalogue.data.remote.response.GenresItem
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.util.SingleLiveEvent
import com.unero.moviecatalogue.util.api.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: Repository) : ViewModel() {

    // Mutable Live Data
    private var _movies = MutableLiveData<List<Movie>>()
    private var _tv = MutableLiveData<List<TVShow>>()
    private var _genresM = MutableLiveData<List<GenresItem>>()
    private var _genresTV = MutableLiveData<List<GenresItem>>()
    val errorMsg = SingleLiveEvent<String>()
    val status = SingleLiveEvent<Boolean>()
    val showLoading = MutableLiveData<Boolean>()

    // Live Data
    val movies: LiveData<List<Movie>> get() = _movies
    val tv: LiveData<List<TVShow>> get() = _tv
    val genreMovies: LiveData<List<GenresItem>> get() = _genresM
    val genreTV: LiveData<List<GenresItem>> get() = _genresTV

    // Get Top Movie
    fun topMovies() {
//        IdlingResources.increment()
        showLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.topMovie()
            showLoading.postValue(false)
            when (result) {
                is APIResponse.Success -> {
                    _movies.postValue(result.data.results)
                }
                is APIResponse.Error -> errorMsg.postValue(result.exception.message)
            }
        }
//        IdlingResources.decrement()
    }

    fun getGenres() {
//        IdlingResources.increment()
        showLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            /**
             * rgm -> Response Genre Movie
             * rgt -> Response Genre Tv Show
             */
            val rgm = repository.genreMovie()
            val rgt = repository.genreTV()
            showLoading.postValue(false)
            when (rgm) {
                is APIResponse.Success -> {
                    _genresM.postValue(rgm.data.genres)
                }
                is APIResponse.Error -> errorMsg.postValue(rgm.exception.message)
            }

            if (rgt is APIResponse.Success)
                _genresTV.postValue(rgt.data.genres)
        }
//        IdlingResources.decrement()
    }

    // Get Top TV Show
    fun topTV() {
//        IdlingResources.increment()
        showLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.topTV()
            showLoading.postValue(false)
            when (result) {
                is APIResponse.Success -> {
                    _tv.postValue(result.data.results)
                }
                is APIResponse.Error -> errorMsg.postValue(result.exception.message)
            }
        }
//        IdlingResources.decrement()
    }

    fun parseGenre(genreId: List<Int>, genres: List<GenresItem>): List<String> {
        val name = mutableListOf<String>()
        genreId.forEach { id ->
            genres.forEach { genre ->
                if (id == genre.id) {
                    name.add(genre.name)
                }
            }
        }
        return name
    }

    // Local
    fun add(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(favorite)
        }
    }

    fun delete(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(favorite)
        }
    }

    fun searchFavorite(id: Int): LiveData<Favorite> {
        return repository.searchFavorite(id)
    }
}