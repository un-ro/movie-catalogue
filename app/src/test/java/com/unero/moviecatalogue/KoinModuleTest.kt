package com.unero.moviecatalogue

import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.viewModelModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest: KoinTest {

    /*
        Unit Test to check each Koin Module
     */

    @Test
    fun `check modules`() = checkModules {
        modules(
                apiModule,
                repositoryModule,
                viewModelModule
        )
    }
}