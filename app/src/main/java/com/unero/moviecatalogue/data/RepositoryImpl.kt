package com.unero.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.unero.moviecatalogue.data.local.LocalDataSource
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.data.remote.Endpoint
import com.unero.moviecatalogue.data.remote.response.GenreResponse
import com.unero.moviecatalogue.data.remote.response.MovieResponse
import com.unero.moviecatalogue.data.remote.response.TVResponse
import com.unero.moviecatalogue.util.api.APIResponse
import com.unero.moviecatalogue.util.api.ResponseHandler.ifError
import com.unero.moviecatalogue.util.api.ResponseHandler.ifSuccess

class RepositoryImpl (
    private val network: Endpoint,
    private val local: LocalDataSource)
    : Repository {

    // Movie, TVShow, Genres
    override suspend fun topMovie(): APIResponse<MovieResponse> {
        return try {
            val response = network.topMovie()
            if (response.isSuccessful) {
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception){
            APIResponse.Error(e)
        }
    }

    override suspend fun topTV(): APIResponse<TVResponse> {
//        IdlingResources.increment()
        return try {
            val response = network.topTV()
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
            val response = network.movieGenre()
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
            val response = network.tvGenre()
            if (response.isSuccessful) {
                ifSuccess(response)
            } else {
                ifError(response)
            }
        } catch (e: Exception) {
            APIResponse.Error(e)
        }
    }

    // Favorite
    override fun getAllFav(type: String): LiveData<PagedList<Favorite>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(local.getFavorites(type), config).build()
    }

    override fun searchFavorite(id: Int): LiveData<Favorite> = local.searchFavorite(id)

    override suspend fun add(favorite: Favorite) {
        local.add(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        local.remove(favorite)
    }
}