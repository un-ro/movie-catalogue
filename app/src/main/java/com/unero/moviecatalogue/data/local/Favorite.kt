package com.unero.moviecatalogue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unero.moviecatalogue.util.Detail

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
    fun toDetail(): Detail {
        return Detail(
            this.id,
            this.originalTitle,
            this.title,
            this.language,
            this.poster,
            this.overview,
            listOf(),
            this.date,
            this.rate,
            listOf(),
            this.isAdult,
            "movie"
        )
    }
}
