package com.unero.moviecatalogue.data

import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse
import com.unero.moviecatalogue.util.api.ResponseUtils.handleApiError
import com.unero.moviecatalogue.util.api.ResponseUtils.handleSuccess

class RepositoryImpl( private val endpoint: Endpoint): Repository {
    override suspend fun topMovie(key: String): APIResponse<MovieResponse> {
        return try {
            val response = endpoint.topMovie(key)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun topTV(key: String): APIResponse<TVResponse> {
        return try {
            val response = endpoint.topTV(key)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun genreMovie(key: String): APIResponse<GenreResponse> {
        return try {
            val response = endpoint.movieGenre(key)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun genreTV(key: String): APIResponse<GenreResponse> {
        return try {
            val response = endpoint.tvGenre(key)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }
}