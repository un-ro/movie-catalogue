package com.unero.moviecatalogue.util

import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    fun setMeta(originalTitle: String, title: String, date: String, language: String): String {
        return if (originalTitle == title)
            "$date \t $language"
        else
            "$title \n $date \t $language"
    }

    // en -> English, etc
    fun setLanguage(originalLanguage: String): String {
        return when (originalLanguage) {
            "en" -> "English"
            "fr" -> "French"
            "ko" -> "Korean"
            "es" -> "Spanish"
            "ja" -> "Japanese"
            else -> originalLanguage
        }
    }

    // Change yyyy-MM-dd -> dd MMMM yyyy
    fun setDateFormat(oldDate: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val newDate = oldFormat.parse(oldDate)
        return newFormat.format(newDate ?: oldDate).toString()
    }

    // Prevent long Movie title
    fun setTitle(title: String): String {
        return if (title.length >= 25) {
            StringBuilder()
                .append(title.take(25))
                .append("...")
                .toString()
        } else {
            title
        }
    }
}