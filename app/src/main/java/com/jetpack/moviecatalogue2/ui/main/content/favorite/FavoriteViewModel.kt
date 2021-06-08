package com.jetpack.moviecatalogue2.ui.main.content.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> =
        movieRepository.getFavoriteTvShows()
}