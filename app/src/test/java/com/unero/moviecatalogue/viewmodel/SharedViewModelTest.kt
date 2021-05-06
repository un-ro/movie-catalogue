package com.unero.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.util.getOrAwaitValue
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertNotNull

class SharedViewModelTest: KoinTest {

    private val viewModel by inject<SharedViewModel>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
                viewModelModule,
                repositoryModule,
                apiModule
        ) // Endpoint -> Repository -> ViewModel
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Top Movies`() {
        // MutableLiveData
        viewModel.topMovies()

        // LiveData
        viewModel.movies.getOrAwaitValue()
        assertNotNull(viewModel.movies.value)
    }

    @Test
    fun `Top TV Show`() {
        // MutableLiveData
        viewModel.topTV()

        // LiveData
        viewModel.tv.getOrAwaitValue()
        assertNotNull(viewModel.tv.value)
    }

    @Test
    fun `Get Genres`() {
        // MutableLiveData
        viewModel.getGenres()

        // LiveData
        viewModel.genreMovies.getOrAwaitValue()
        viewModel.genreTV.getOrAwaitValue()

        assertNotNull(viewModel.genreMovies.value)
        assertNotNull(viewModel.genreTV.value)
    }
}