package com.unero.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.BuildConfig
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.remote.response.GenresItem
import com.unero.moviecatalogue.data.remote.response.Movie
import com.unero.moviecatalogue.data.remote.response.TVShow
import com.unero.moviecatalogue.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: Repository) : ViewModel() {

    private val apiKey = BuildConfig.KEY

    // Mutable Live Data
    private var _movies = MutableLiveData<List<Movie>>()
    private var _tv = MutableLiveData<List<TVShow>>()
    private var _genresM = MutableLiveData<List<GenresItem>>()
    private var _genresTV = MutableLiveData<List<GenresItem>>()
    val errorMsg = SingleLiveEvent<String>()

    // Live Data
    val movies: LiveData<List<Movie>> get() = _movies
    val tv: LiveData<List<TVShow>> get() = _tv
    val genreMovies: LiveData<List<GenresItem>> get() = _genresM
    val genreTV: LiveData<List<GenresItem>> get() = _genresTV

    // Get Top Movie
    fun topMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.topMovie(apiKey)
                if (response.isSuccessful) {
                    _movies.postValue(response.body()?.results)
                } else {
                    errorMsg.postValue(messageTemplate(
                        response.code(),
                        response.errorBody().toString()
                    ))
                }
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                /**
                 * rgm -> Response Genre Movie
                 * rgt -> Response Genre Tv Show
                 */
                val rgm = repository.genreMovie(apiKey)
                val rgt = repository.genreTV(apiKey)
                if (rgm.isSuccessful && rgt.isSuccessful) {
                    _genresM.postValue(rgm.body()?.genres)
                    _genresTV.postValue(rgt.body()?.genres)
                } else {
                    errorMsg.postValue(messageTemplate(
                        rgm.code(),
                        rgm.errorBody().toString()
                    ))
                }
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
    }

    // Get Top TV Show
    fun topTV() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.topTV(apiKey)
                if (response.isSuccessful) {
                    _tv.postValue(response.body()?.results)
                } else {
                    errorMsg.postValue(messageTemplate(
                        response.code(),
                        response.errorBody().toString()
                    ))
                }
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }
        }
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

    private fun messageTemplate(code: Int, body: String): String? {
        return "Error $code - $body"
    }
}