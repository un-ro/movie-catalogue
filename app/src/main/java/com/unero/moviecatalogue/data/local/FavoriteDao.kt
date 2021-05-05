package com.unero.moviecatalogue.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert()

    @Delete
    fun delete()
}
