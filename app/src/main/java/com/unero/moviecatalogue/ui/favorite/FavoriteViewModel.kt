package com.unero.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.data.local.entity.Favorite

class FavoriteViewModel(private val repository: Repository): ViewModel() {
    // Status Only
    var showLoading = MutableLiveData<Boolean>()

    fun setLoad(boolean: Boolean) {
        showLoading.postValue(boolean)
    }

    fun getFav(type: String): LiveData<PagedList<Favorite>> = repository.getAllFav(type)
}