package com.unero.moviecatalogue.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val vote_average: Float,
    val original_language: String,
    val adult: Boolean
): Parcelable
