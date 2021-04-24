package com.unero.moviecatalogue.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val rate: Float,
    @SerializedName("original_language")
    val language: String,
    @SerializedName("adult")
    val isAdult: Boolean
): Parcelable
