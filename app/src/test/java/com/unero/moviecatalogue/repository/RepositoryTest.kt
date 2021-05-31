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
import kotlin.test.assertNull

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
            when (val response = repository.topMovie()) {
                is APIResponse.Success -> {
                    println(response.data)
                    assertNotNull(response.data)
                }
                is APIResponse.Error -> {
                    println(response.exception.message)
                }
            }
        }
    }

    @Test
    fun `Get TVShow from Network`() {
        runBlocking {
            when (val response = repository.topTV()) {
                is APIResponse.Success -> {
                    println(response.data)
                    assertNotNull(response.data)
                }
                is APIResponse.Error -> {
                    println(response.exception.message)
                }
            }
        }
    }

    @Test
    fun `Get Movie Genre and check if equal to Generate`() {
        runBlocking {
            when(val response = repository.genreMovie()) {
                is APIResponse.Success -> assertEquals(fakeDataSuccess, response.data.genres)
                else -> assertNull(response)
            }
        }
    }

    @Test
    fun `Get Movie Genre and check if different to Generate`() {
        runBlocking {
            when(val response = repository.genreMovie()) {
                is APIResponse.Success -> assertNotEquals(fakeDataFail, response.data.genres)
                else -> assertNull(response)
            }
        }
    }
}