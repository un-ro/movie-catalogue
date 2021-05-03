package com.unero.moviecatalogue

import com.unero.moviecatalogue.data.Repository
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.viewModelModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class RepositoryTest: KoinTest {

    private val apiKey = BuildConfig.KEY
    private val repository by inject<Repository>()

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
    fun `coba dong`() {
        repository.topMovieTest(apiKey)
    }
}