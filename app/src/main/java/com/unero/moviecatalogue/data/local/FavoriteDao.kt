package com.unero.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun loadFavoriteByType(type: String): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Favorite)

    @Delete
    fun delete(item: Favorite)
}