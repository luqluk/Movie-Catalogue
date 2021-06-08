package com.jetpack.moviecatalogue2.ui.main

import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jetpack.moviecatalogue2.R
import com.jetpack.moviecatalogue2.databinding.ActivityMainBinding
import com.jetpack.moviecatalogue2.ui.main.content.movie.MovieViewModel
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f

        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        )[MovieViewModel::class.java]


        val navView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movie, R.id.navigation_tv, R.id.navigation_fav
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

}