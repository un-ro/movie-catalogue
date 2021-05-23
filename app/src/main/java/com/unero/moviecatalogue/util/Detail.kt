package com.unero.moviecatalogue.util

import android.os.Parcelable
import com.unero.moviecatalogue.data.local.entity.Favorite
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
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
): Parcelable {
    fun toFavorite(): Favorite {
        return Favorite(
                this.id,
                this.originalTitle,
                this.title,
                this.language,
                this.poster,
                this.overview,
                this.date,
                this.rate,
                this.type
        )
    }
}