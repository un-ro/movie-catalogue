package com.unero.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.util.SingleLiveEvent
import com.unero.moviecatalogue.util.api.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository): ViewModel() {
    // Mutable Live Data
    private var _movies = MutableLiveData<List<Movie>>()
    private var _tv = MutableLiveData<List<TVShow>>()

    // Status Only
    val errorMsg = SingleLiveEvent<String>()
    val showLoading = MutableLiveData<Boolean>()

    // Live Data
    val movies: LiveData<List<Movie>> get() = _movies
    val tv: LiveData<List<TVShow>> get() = _tv

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
}