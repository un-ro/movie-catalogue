package com.unero.moviecatalogue.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.unero.moviecatalogue.databinding.ActivitySplashBinding
import com.unero.moviecatalogue.ui.home.HomeActivity
import com.unero.moviecatalogue.util.IdlingResources
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)

        // Delay
        lifecycleScope.launch {
            IdlingResources.increment()
            delay(2500)
            startActivity(intent)
            IdlingResources.decrement()
            finish()
        }
    }
}
