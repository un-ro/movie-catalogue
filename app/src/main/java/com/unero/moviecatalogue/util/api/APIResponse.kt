package com.unero.moviecatalogue.util.api

sealed class APIResponse<out T> {
    // Class for success API Call
    data class Success<out T>(val data: T): APIResponse<T>()
    // Class for error API Call
    class Error( val exception: Exception ): APIResponse<Nothing>()
}