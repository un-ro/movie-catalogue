package com.unero.moviecatalogue.util.api

import retrofit2.Response

object ResponseUtils {
    fun <T : Any> handleApiError(response: Response<T>): APIResponse.Error {
        val error = ApiErrorUtils.parseError(response)
        return APIResponse.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): APIResponse<T> {
        response.body()?.let {
            return APIResponse.Success(it)
        } ?: return handleApiError(response)
    }
}