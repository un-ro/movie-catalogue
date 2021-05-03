package com.unero.moviecatalogue

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.fakedata.FakeGenre
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class GenreTest: KoinTest {

    /*
        Unit Test to test generated / manual object with data from API
     */

    private val apiKey = BuildConfig.KEY
    private val repository by inject<Repository>()
    private val fakeDataSuccess = FakeGenre().generateUpdated()
    private val fakeDataFail = FakeGenre().generateDiffer()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
                repositoryModule,
                viewModelModule,
                apiModule
        )
    }

    @Test
    fun `check if genres is same`() {
        val response = repository.genreTest(apiKey)
        val data = response.body()?.genres
        assertEquals(fakeDataSuccess, data)
    }

    @Test
    fun `check if genres is different`() {
        val response = repository.genreTest(apiKey)
        val data = response.body()?.genres
        assertNotEquals(fakeDataFail, data)
    }
}