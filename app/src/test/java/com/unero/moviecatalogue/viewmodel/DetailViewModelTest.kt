package com.unero.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.unero.moviecatalogue.data.RepositoryImpl
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.roomModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.fakedata.ItemFaker
import com.unero.moviecatalogue.ui.detail.DetailViewModel
import com.unero.moviecatalogue.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertNotNull
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DetailViewModelTest: KoinTest {

    private val viewModel by inject<DetailViewModel>()
    private lateinit var vmSpy: DetailViewModel
    private lateinit var real: RepositoryImpl
    private lateinit var spy: RepositoryImpl
    private val dispatch = TestCoroutineDispatcher()

    private val favorite = ItemFaker.generateFavorite()

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
        Dispatchers.setMain(dispatch)
        real = RepositoryImpl(get(), get())
        spy = Mockito.spy(real)
        vmSpy = DetailViewModel(spy)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
        Mockito.reset(spy)
    }

    @Test
    fun `A Get Genres`() {
        // Given
        viewModel.getGenres()

        // When
        viewModel.genreMovies.getOrAwaitValue()
        viewModel.genreTV.getOrAwaitValue()

        // Then
        assertNotNull(viewModel.genreMovies.value)
        assertNotNull(viewModel.genreTV.value)
    }

    @Test
    fun `B Parse Genres`() {
        // Given
        val genres = ItemFaker.generateUpdated()
        val genreId = listOf(28, 35, 80)

        // When
        val expected = listOf("Action", "Comedy", "Crime")

        // Then
        assertEquals(expected, viewModel.parseGenre(genreId, genres))
    }

    @Test
    fun `C Add Favorite`() {
        // Given
        vmSpy.add(favorite)

        runBlocking {
            verify(spy).add(favorite)
        }
    }

    @Test
    fun `D Search Favorite`() {
        vmSpy.searchFavorite(favorite.id)

        runBlocking {
            verify(spy).searchFavorite(favorite.id)
        }
    }

    // Try to just run this without one package or one class
    @Test
    fun `E Delete Favorite`() {
        vmSpy.delete(favorite)
        assertNotNull(favorite)
        runBlocking {
            verify(spy).delete(favorite)
            verifyNoMoreInteractions(spy)
        }
    }
}