package com.unero.moviecatalogue.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<Movie>,

	@field:SerializedName("total_results")
	val totalResults: Int
) : Parcelable

@Parcelize
data class Movie(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val language: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val date: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val rate: Float,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val isAdult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int
) : Parcelable
