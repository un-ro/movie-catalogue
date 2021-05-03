package com.unero.moviecatalogue.util

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResources {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}