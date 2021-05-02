package com.unero.moviecatalogue

import com.unero.moviecatalogue.data.RepositoryImpl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest {

    private val api_key = BuildConfig.KEY
    private val server = MockWebServer()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        server.start()
    }

    // Movies
    @Test
    fun `fetch movie and check isSuccessful`() {
        val response = RepositoryImpl.testMovie(api_key).execute()

        assertEquals(true, response.isSuccessful)
    }

    @Test
    fun `check one title in movie`() {
        val response = RepositoryImpl.testMovie(api_key).execute()

        assertEquals("Godzilla vs. Kong", response.body()?.results?.get(0)?.title)
    }

    // TVShow
    @Test
    fun `fetch tvShow and check isSuccessful`() {
        val response = RepositoryImpl.testTV(api_key).execute()

        assertEquals(true, response.isSuccessful)
    }

    @Test
    fun `check one title in tvshow`() {
        val response = RepositoryImpl.testTV(api_key).execute()

        assertEquals("The Falcon and the Winter Soldier", response.body()?.results?.get(0)?.original_name)
    }

    // Wrong API Key
    @Test
    fun `false api key`() {
        val response = RepositoryImpl.testTV("").execute()

        assertEquals(false, response.isSuccessful)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}