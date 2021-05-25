package com.unero.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.test.core.app.ApplicationProvider
import com.unero.moviecatalogue.data.RepositoryImpl
import com.unero.moviecatalogue.data.local.LocalDataSource
import com.unero.moviecatalogue.data.local.entity.Favorite
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.roomModule
import com.unero.moviecatalogue.di.viewModelModule
import com.unero.moviecatalogue.fakedata.ItemFaker
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import com.unero.moviecatalogue.util.PagedListUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class FavoriteViewModelTest: KoinTest {

    private val viewModel by inject<FavoriteViewModel>()
    private val local = Mockito.mock(LocalDataSource::class.java)
    private lateinit var repository: RepositoryImpl

    // Need to access DB
    private val dispatch = TestCoroutineDispatcher()

    // Live Data access
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
        repository = RepositoryImpl(get(), local)
        Dispatchers.setMain(dispatch)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun `Get PagedList but empty`(){
        val favorite = viewModel.getFav("movie").value?.snapshot()
        assertEquals(true, favorite.isNullOrEmpty())
    }

    @Test
    fun `PagedList but not empty`(){
        val list = mutableListOf<Favorite>()
        for (i in 0..10) {
            list.add(ItemFaker.generateFavorite())
        }

        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Favorite>
        Mockito.`when`(local.getFavorites("movie")).thenReturn(dataSource)
        repository.getAllFav("movie")

        val check = PagedListUtil.mockPagedList(list)
        println(check)
        Mockito.verify(local).getFavorites("movie")
    }
}
