package com.jetpack.moviecatalogue2.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.moviecatalogue2.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    companion object {
        const val INTERVAL = 2000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            binding.progressHorizontal.progress = INTERVAL - 1000
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, INTERVAL.toLong())
    }
}