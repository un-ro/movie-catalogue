package com.unero.moviecatalogue.util.api

import android.util.Log
import com.google.gson.GsonBuilder
import okio.IOException
import retrofit2.Response

object ApiErrorUtils {

    fun parseError(response: Response<*>): APIError {

        val gson = GsonBuilder().create()
        val error: APIError

        try {
            error = gson.fromJson(response.errorBody()?.string(), APIError::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d("ERROR API", it) }
            return APIError()
        }
        return error
    }
}

data class APIError(val message: String) {
    constructor() : this("")
}