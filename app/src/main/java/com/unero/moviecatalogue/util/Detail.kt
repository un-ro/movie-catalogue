package com.unero.moviecatalogue.util

import android.os.Parcelable
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
    var country: List<String>?,
    var isAdult: Boolean = false,
    val type: String
): Parcelable