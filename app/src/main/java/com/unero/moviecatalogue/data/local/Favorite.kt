package com.unero.moviecatalogue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: String,
    val poster: String,
    val type: String
)
