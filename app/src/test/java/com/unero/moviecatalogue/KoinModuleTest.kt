package com.unero.moviecatalogue

import androidx.test.core.app.ApplicationProvider
import com.unero.moviecatalogue.di.apiModule
import com.unero.moviecatalogue.di.repositoryModule
import com.unero.moviecatalogue.di.roomModule
import com.unero.moviecatalogue.di.viewModelModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class KoinModuleTest: KoinTest {

    @Test
    fun `Check All Modules`() = checkModules {
        androidContext(ApplicationProvider.getApplicationContext())
        modules(
            apiModule,
            roomModule,
            repositoryModule,
            viewModelModule
        )
    }
}