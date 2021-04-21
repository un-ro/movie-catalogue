package com.unero.moviecatalogue.util

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.*

class Detail {
    private val res = Resources.getSystem()

    // en -> English, etc
    fun setLanguage(originalLanguage: String): String {
        return when (originalLanguage) {
            "en" -> "English"
            "fr" -> "French"
            "ko" -> "Korean"
            "es" -> "Spanish"
            else -> "N/A"
        }
    }

    // Change yyyy-MM-dd -> dd MMMM yyyy
    fun setDateFormat(oldDate: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val newDate = oldFormat.parse(oldDate)
        return newFormat.format(newDate ?: oldDate).toString()
    }
}