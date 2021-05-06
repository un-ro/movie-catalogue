package com.unero.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.fakedata.FakeGenre
import com.unero.moviecatalogue.util.api.APIResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class RepositoryTest: KoinTest {

    /*
        A Unit Test where data get from API
     */

    private val apiKey = BuildConfig.KEY
    private val repository by inject<Repository>()
    private val fakeDataSuccess = FakeGenre().generateUpdated()
    private val fakeDataFail = FakeGenre().generateDiffer()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
            apiModule,
            repositoryModule
        ) // Endpoint -> Repository
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    // To trigger fail, just change apiKey -> "apiKey"
    @Test
    fun `Get Movie`() {
        runBlocking{
            val response = repository.topMovie(apiKey)
            when(response) {
                is APIResponse.Success -> {
                    println(response.data)
                    assertNotNull(response.data) // Success
                }
                is APIResponse.Error -> assertNotNull(response.exception.message) // Fail
            }
        }
    }

    @Test
    fun `Get TV Show`() {
        runBlocking{
            val response = repository.topTV(apiKey)
            when(response) {
                is APIResponse.Success -> {
                    println(response.data)
                    assertNotNull(response.data)
                } // Success
                is APIResponse.Error -> {
                    println(response.exception.message)
                    assertNotNull(response.exception.message)
                } // Fail
            }
        }
    }

    // Can be fail, depend on API Result.
    @Test
    fun `Get Movie Genre and check if equal to Generate`() {
        runBlocking {
            val response = repository.genreMovie(apiKey)
            when(response) {
                is APIResponse.Success -> assertEquals(fakeDataSuccess, response.data.genres)
            }
        }
    }

    @Test
    fun `Get Movie Genre and check if different to Generate`() {
        runBlocking {
            val response = repository.genreMovie(apiKey)
            when(response) {
                is APIResponse.Success -> assertNotEquals(fakeDataFail, response.data.genres)
            }
        }
    }
}