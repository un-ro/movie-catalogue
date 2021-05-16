package com.unero.moviecatalogue.data.remote.response

import com.google.gson.annotations.SerializedName
import com.unero.moviecatalogue.util.Detail

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

data class TVShow(
		@field:SerializedName("id")
		val id: Int,

        @field:SerializedName("original_name")
		val originalTitle: String,

        @field:SerializedName("name")
		val title: String,

        @field:SerializedName("original_language")
		val language: String,

        @field:SerializedName("poster_path")
		val poster: String,

        @field:SerializedName("overview")
		val overview: String,

        @field:SerializedName("genre_ids")
		val genreIds: List<Int>,

        @field:SerializedName("first_air_date")
		val date: String,

        @field:SerializedName("vote_average")
		val rate: Float,

        @field:SerializedName("origin_country")
		val country: List<String>,

        var type: String = "tv"
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
				this.country,
				false,
				"tv"
		)
	}
}
