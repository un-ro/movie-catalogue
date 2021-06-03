package com.unero.moviecatalogue.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.roomModule
import com.unero.moviecatalogue.fakedata.ItemFaker.generateDiffer
import com.unero.moviecatalogue.fakedata.ItemFaker.generateUpdated
import com.unero.moviecatalogue.util.api.APIResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class RepositoryTest: KoinTest {

    // Repository Test
    private val repository by inject<Repository>()

    private val fakeDataSuccess = generateUpdated()
    private val fakeDataFail = generateDiffer()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                apiModule,
                roomModule,
                repositoryModule
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Get Movie from Network`() {
        runBlocking {
            val response = repository.topMovie() as APIResponse.Success
            assertNotNull(response.data)
        }
    }

    @Test
    fun `Get Movie from Network and have size 20`() {
        runBlocking {
            val response = repository.topMovie() as APIResponse.Success
            assertEquals(20, response.data.results.size)
        }
    }

    @Test
    fun `Get TVShow from Network`() {
        runBlocking {
            val response = repository.topTV() as APIResponse.Success
            assertNotNull(response.data)
        }
    }

    @Test
    fun `Get TVShow from Network and have size 20`() {
        runBlocking {
            val response = repository.topTV() as APIResponse.Success
            assertEquals(20, response.data.results.size)
        }
    }

    @Test
    fun `Get Movie Genre and check if equal to Generate`() {
        runBlocking {
            val response = repository.genreMovie() as APIResponse.Success
            assertEquals(fakeDataSuccess, response.data.genres)
        }
    }

    @Test
    fun `Get Movie Genre and check if different to Generate`() {
        runBlocking {
            val response = repository.genreMovie() as APIResponse.Success
            assertNotEquals(fakeDataFail, response.data.genres)
        }
    }
}