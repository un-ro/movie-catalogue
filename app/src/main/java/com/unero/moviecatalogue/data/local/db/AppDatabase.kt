package com.unero.moviecatalogue.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unero.moviecatalogue.data.local.dao.FavoriteDao
import com.unero.moviecatalogue.data.local.entity.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}