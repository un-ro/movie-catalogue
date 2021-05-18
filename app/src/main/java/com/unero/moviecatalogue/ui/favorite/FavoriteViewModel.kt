package com.unero.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.local.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository): ViewModel() {
    // Mutable Live Data
    private var _favorites = MutableLiveData<List<Favorite>>()

    // Live Data
    val favorites: LiveData<List<Favorite>> get() = _favorites

    fun getFav(type: String){
        viewModelScope.launch(Dispatchers.IO) {
            _favorites.postValue(repository.getAllFav(type))
        }
    }
}