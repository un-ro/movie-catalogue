package com.unero.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.unero.moviecatalogue.data.local.Favorite
import com.unero.moviecatalogue.data.local.FavoriteDao
import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse
import com.unero.moviecatalogue.util.api.ResponseHandler.ifError
import com.unero.moviecatalogue.util.api.ResponseHandler.ifSuccess

class RepositoryImpl( private val endpoint: Endpoint, private val dao: FavoriteDao): Repository {
    override suspend fun topMovie(): APIResponse<MovieResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.topMovie()
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

    override suspend fun topTV(): APIResponse<TVResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.topTV()
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

    override suspend fun genreMovie(): APIResponse<GenreResponse> {
//        IdlingResources.increment()
        return try {
            val response = endpoint.movieGenre()
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

    override suspend fun genreTV(): APIResponse<GenreResponse> {
        return try {
            val response = endpoint.tvGenre()
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
    override suspend fun getAllFav(type: String): List<Favorite> {
        return dao.loadFavoriteByType(type)
    }

    override fun searchFavorite(id: Int): LiveData<Favorite> {
        return dao.searchFavorite(id)
    }

    override suspend fun add(favorite: Favorite) {
        dao.insert(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        dao.delete(favorite)
    }
}