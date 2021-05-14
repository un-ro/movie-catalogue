package com.unero.moviecatalogue.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<Movie>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

@Parcelize
data class Movie(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("original_language")
	val language: String,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("release_date")
	val date: String,

	@field:SerializedName("vote_average")
	val rate: Float,

	@field:SerializedName("adult")
	val isAdult: Boolean,

	var type: String
) : Parcelable
