package com.unero.moviecatalogue.data

import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.EspressoIdlingResources
import com.unero.moviecatalogue.util.api.APIResponse
import com.unero.moviecatalogue.util.api.ResponseUtils.handleApiError
import com.unero.moviecatalogue.util.api.ResponseUtils.handleSuccess
import retrofit2.Response

class RepositoryImpl( private val endpoint: Endpoint): Repository {
    override suspend fun topMovie(key: String): APIResponse<MovieResponse> {
        EspressoIdlingResources.increment()
        return try {
            val response = endpoint.topMovie(key)
            if (response.isSuccessful) {
                EspressoIdlingResources.decrement()
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun topTV(key: String): APIResponse<TVResponse> {
        EspressoIdlingResources.increment()
        return try {
            val response = endpoint.topTV(key)
            if (response.isSuccessful) {
                EspressoIdlingResources.decrement()
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun genreMovie(key: String): APIResponse<GenreResponse> {
        EspressoIdlingResources.increment()
        return try {
            val response = endpoint.movieGenre(key)
            if (response.isSuccessful) {
                EspressoIdlingResources.decrement()
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

    // Unit Test API
    override fun movieTest(key: String): Response<MovieResponse> {
        return endpoint.testMovie(key).execute()
    }

    override fun tvTest(key: String): Response<TVResponse> {
        return endpoint.testTV(key).execute()
    }

    override fun genreTest(key: String): Response<GenreResponse> {
        return endpoint.testGenre(key).execute()
    }
}