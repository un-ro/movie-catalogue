package com.unero.moviecatalogue.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.unero.moviecatalogue.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun loadFavoriteByType(type: String): DataSource.Factory<Int, Favorite>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun searchFavorite(id: Int): LiveData<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Favorite)

    @Delete
    fun delete(item: Favorite)
}