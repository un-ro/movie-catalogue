package com.unero.moviecatalogue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val rate: Float,
    val date: String,
    val poster: String,
    val type: String,
    val genres: List<Int>,
    val overview: String,
    val otherData: String
)
