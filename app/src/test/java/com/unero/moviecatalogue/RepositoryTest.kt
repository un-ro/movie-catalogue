package com.unero.moviecatalogue

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RepositoryTest: KoinTest {

    /*
        A Unit Test where data get from API
     */

    private val apiKey = BuildConfig.KEY
    private val repository by inject<Repository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
            repositoryModule,
            apiModule
        )
    }

    @Test
    fun `request api and success`() {
        val response = repository.movieTest(apiKey)
        assertTrue { response.isSuccessful }
    }

    @Test
    fun `request api and fail`() {
        val response = repository.movieTest("iforgotmykey")
        assertFalse { response.isSuccessful }
    }

    @Test
    fun `request movie`() {
        val response = repository.movieTest(apiKey)
        val data = response.body()?.results
        assertNotNull(data)
    }

    @Test
    fun `request tv show`() {
        val response = repository.tvTest(apiKey)
        val data = response.body()?.results
        assertNotNull(data)
    }

    @Test
    fun `request tv show but null`() {
        val response = repository.tvTest("iforgotmykeyagain")
        val data = response.body()?.results
        assertNull(data)
    }
}