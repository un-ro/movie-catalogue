package com.unero.moviecatalogue.data.local.entity

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
    val genreIds: List<Int>,
    val date: String,
    val rate: Float,
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
            this.genreIds,
            this.date,
            this.rate,
            this.type
        )
    }
}
