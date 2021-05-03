package com.unero.moviecatalogue

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.viewmodel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest: KoinTest {

    private val apiKey = BuildConfig.KEY
    private val server = MockWebServer()
    val repository by inject<Repository>()
    val viewModel by inject<SharedViewModel>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
                repositoryModule,
                viewModelModule,
                apiModule
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        server.start()
    }

    @Test
    suspend fun `asd test`() {
        return withContext(Dispatchers.Default) {
            val response = repository.topMovie(apiKey)
            return@withContext
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}