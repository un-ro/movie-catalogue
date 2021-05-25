package com.unero.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.remote.response.GenresItem
import com.unero.moviecatalogue.util.SingleLiveEvent
import com.unero.moviecatalogue.util.api.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {
    // Mutable Live Data
    private var _genresM = MutableLiveData<List<GenresItem>>()
    private var _genresTV = MutableLiveData<List<GenresItem>>()

    // Status Only
    private val showLoading = MutableLiveData<Boolean>()
    val errorMsg = SingleLiveEvent<String>()
    val status = SingleLiveEvent<Boolean>()

    // Live Data
    val genreMovies: LiveData<List<GenresItem>> get() = _genresM
    val genreTV: LiveData<List<GenresItem>> get() = _genresTV

    fun getGenres() {
        // IdlingResources.increment()
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
        // IdlingResources.decrement()
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
        //IdlingResources.increment()
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(favorite)
            //IdlingResources.decrement()
        }
    }

    fun delete(favorite: Favorite){
        //IdlingResources.increment()
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(favorite)
            //IdlingResources.decrement()
        }
    }

    fun searchFavorite(id: Int): LiveData<Favorite> = repository.searchFavorite(id)
}