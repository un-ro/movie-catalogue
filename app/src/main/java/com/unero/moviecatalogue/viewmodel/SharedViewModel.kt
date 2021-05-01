package com.unero.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.BuildConfig
import com.unero.moviecatalogue.data.remote.Repository
import com.unero.moviecatalogue.data.remote.model.GenreResponse
import com.unero.moviecatalogue.data.remote.model.MovieResponse
import com.unero.moviecatalogue.data.remote.model.TVResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel : ViewModel() {

    private val api_key = BuildConfig.KEY

    // Mutable Live Data
    private var _movies: MutableLiveData<Response<MovieResponse>> = MutableLiveData()
    private var _tv: MutableLiveData<Response<TVResponse>> = MutableLiveData()
    private var _genresM: MutableLiveData<Response<GenreResponse>> = MutableLiveData()
    private var _genresTV: MutableLiveData<Response<GenreResponse>> = MutableLiveData()
    var errorMsg: MutableLiveData<String> = MutableLiveData()

    // Live Data
    val movies: LiveData<Response<MovieResponse>> get() = _movies
    val genreMovies: LiveData<Response<GenreResponse>> get() = _genresM
    val genreTV: LiveData<Response<GenreResponse>> get() = _genresTV
    val tv: LiveData<Response<TVResponse>> get() = _tv

    // Get Top Movie
    fun topMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Repository.topMovie(api_key))
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _genresM.postValue(Repository.genreMovie(api_key))
                _genresTV.postValue(Repository.genreTV(api_key))
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }

    // Get Top TV Show
    fun topTV() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _tv.postValue(Repository.topTV(api_key))
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }
}