package com.unero.moviecatalogue.data.model

data class TVShow(
    val id: Int,
    val original_name: String,
    val overview: String,
    val first_air_date: String,
    val vote_average: Float,
    val poster_path: String
)
