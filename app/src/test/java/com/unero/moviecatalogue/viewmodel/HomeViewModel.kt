package com.unero.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.roomModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.ui.home.HomeViewModel
import com.unero.moviecatalogue.util.getOrAwaitValue
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
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HomeViewModel: KoinTest {

    private val viewModel by inject<HomeViewModel>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                apiModule,
                roomModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Top Movies Success`() {
        // Given
        viewModel.topMovies()
        // When
        viewModel.movies.getOrAwaitValue()
        // Then
        assertNotNull(viewModel.movies.value)
    }

    @Test
    fun `Top TVShow Success`() {
        // Given
        viewModel.topTV()
        // When
        viewModel.tv.getOrAwaitValue()
        // Then
        assertNotNull(viewModel.tv.value)
    }
}