package com.unero.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.unero.moviecatalogue.data.local.FavoriteDao
import com.unero.moviecatalogue.data.local.FavoriteEntity
import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse
import com.unero.moviecatalogue.util.api.ResponseHandler.ifError
import com.unero.moviecatalogue.util.api.ResponseHandler.ifSuccess

class RepositoryImpl( private val endpoint: Endpoint, private val dao: FavoriteDao): Repository {
    override suspend fun topMovie(key: String): APIResponse<MovieResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.topMovie(key)
            if (response.isSuccessful) {
//                IdlingResources.decrement()
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun topTV(key: String): APIResponse<TVResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.topTV(key)
            if (response.isSuccessful) {
//                IdlingResources.decrement()
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun genreMovie(key: String): APIResponse<GenreResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.movieGenre(key)
            if (response.isSuccessful) {
//                IdlingResources.decrement()
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    override suspend fun genreTV(key: String): APIResponse<GenreResponse> {
        return try {
            val response = endpoint.tvGenre(key)
            if (response.isSuccessful) {
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    // Room
    override suspend fun getAllFav(type: String): LiveData<List<FavoriteEntity>> {
        return dao.loadFavoriteByType(type)
    }
}