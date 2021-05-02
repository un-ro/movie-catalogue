package com.unero.moviecatalogue.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TVResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TVShow>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

@Parcelize
data class TVShow(

	@field:SerializedName("first_air_date")
	val date: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val language: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("origin_country")
	val country: List<String>,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val rate: Float,

	@field:SerializedName("name")
	val title: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int
) : Parcelable
