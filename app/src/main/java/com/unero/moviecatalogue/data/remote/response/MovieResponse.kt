package com.unero.moviecatalogue.data.remote.response

import com.google.gson.annotations.SerializedName
import com.unero.moviecatalogue.util.Detail

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

	var type: String
) {
	fun toDetail(): Detail {
		return Detail(
				this.id,
				this.originalTitle,
				this.title,
				this.language,
				this.poster,
				this.overview,
				this.genreIds,
				this.date,
				this.rate,
				"movie"
		)
	}
}
