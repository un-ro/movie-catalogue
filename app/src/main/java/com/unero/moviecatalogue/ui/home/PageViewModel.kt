package com.unero.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unero.moviecatalogue.BuildConfig
import com.unero.moviecatalogue.data.api.Repository
import com.unero.moviecatalogue.data.model.ResultMovie
import com.unero.moviecatalogue.data.model.ResultShows
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class PageViewModel : ViewModel() {

    private val api_key = BuildConfig.KEY

    // Mutable Live Data
    private var _movies: MutableLiveData<Response<ResultMovie>> = MutableLiveData()
    private var _tv: MutableLiveData<Response<ResultShows>> = MutableLiveData()

    // Live Data
    val movies: LiveData<Response<ResultMovie>>
        get()  {
            return _movies
        }

    val tv: LiveData<Response<ResultShows>>
        get() {
            return _tv
        }

    // Get
    fun topMovies() {
        GlobalScope.launch {
            try {
                _movies.postValue(Repository.topMovie(api_key))
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun topTV() {
        GlobalScope.launch {
            try {
                _tv.postValue(Repository.topTV(api_key))
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }
}