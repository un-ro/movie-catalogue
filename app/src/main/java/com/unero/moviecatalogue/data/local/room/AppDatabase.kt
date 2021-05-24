package com.unero.moviecatalogue.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.local.entity.GenreConverter

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}