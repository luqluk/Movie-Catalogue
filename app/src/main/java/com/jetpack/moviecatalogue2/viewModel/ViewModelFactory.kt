package com.jetpack.moviecatalogue2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.ui.detail.DetailViewModel
import com.jetpack.moviecatalogue2.ui.main.content.favorite.FavoriteViewModel
import com.jetpack.moviecatalogue2.ui.main.content.movie.MovieViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mMovieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}