package com.unero.moviecatalogue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val originalTitle: String,
    val title: String,
    val language: String,
    val poster: String,
    val overview: String,
    val date: String,
    val rate: Float,
    val isAdult: Boolean,
    val type: String
) {
    fun toDetail() {
    }
}
