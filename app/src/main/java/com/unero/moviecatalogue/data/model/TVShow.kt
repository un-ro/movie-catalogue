package com.unero.moviecatalogue.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShow(
    val id: Int,
    @SerializedName("original_name")
    val title: String,
    val overview: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("vote_average")
    val rate: Float,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("origin_country")
    val country: List<String>,
    @SerializedName("original_language")
    val language: String
): Parcelable
