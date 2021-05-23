package com.unero.moviecatalogue.di

import android.app.Application
import androidx.room.Room
import com.unero.moviecatalogue.data.local.dao.FavoriteDao
import com.unero.moviecatalogue.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module{
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "moviecdb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: AppDatabase): FavoriteDao {
        return database.favDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}