package com.unero.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun loadFavoriteByType(type: String): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: FavoriteEntity)

    @Delete
    fun delete(item: FavoriteEntity)
}