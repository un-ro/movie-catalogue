package com.unero.moviecatalogue.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    val id: Int,
    val original_name: String,
    val overview: String,
    val first_air_date: String,
    val vote_average: Float,
    val poster_path: String
): Parcelable
