package com.unero.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.local.room.FavoriteDao

class LocalDataSource(private val dao: FavoriteDao) {
    fun getFavorites(type: String): DataSource.Factory<Int, Favorite> = dao.loadFavoriteByType(type)
    fun searchFavorite(id: Int): LiveData<Favorite> = dao.searchFavorite(id)
    fun add(favorite: Favorite) = dao.insert(favorite)
    fun remove(favorite: Favorite) = dao.delete(favorite)
}