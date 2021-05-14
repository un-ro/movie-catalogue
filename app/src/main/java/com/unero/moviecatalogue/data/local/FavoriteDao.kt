package com.unero.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun loadFavoriteByType(type: String): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun searchFavorite(id: Int): LiveData<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Favorite)

    @Delete
    fun delete(item: Favorite)
}