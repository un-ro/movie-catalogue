package com.unero.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unero.moviecatalogue.BuildConfig
import com.unero.moviecatalogue.data.api.Repository
import com.unero.moviecatalogue.data.model.MovieResponse
import com.unero.moviecatalogue.data.model.TVResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class PageViewModel : ViewModel() {

    private val api_key = BuildConfig.KEY

    // Mutable Live Data
    private var _movies: MutableLiveData<Response<MovieResponse>> = MutableLiveData()
    private var _tv: MutableLiveData<Response<TVResponse>> = MutableLiveData()
    var errorMsg: MutableLiveData<String> = MutableLiveData()

    // Live Data
    val movies: LiveData<Response<MovieResponse>>
        get()  {
            return _movies
        }

    val tv: LiveData<Response<TVResponse>>
        get() {
            return _tv
        }

    // Get Top Movie
    fun topMovies() {
        GlobalScope.launch {
            try {
                _movies.postValue(Repository.topMovie(api_key))
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }

    // Get Top TV Show
    fun topTV() {
        GlobalScope.launch {
            try {
                _tv.postValue(Repository.topTV(api_key))
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }
}