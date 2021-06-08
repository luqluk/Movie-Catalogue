package com.jetpack.moviecatalogue2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.remote.response.MovieResponse

class DetailViewModel (private val movieRepository: MovieRepository) : ViewModel() {

    fun detailMovie(movieId: Int): LiveData<MovieEntity> =
        movieRepository.getDetailMovie(movieId)

    fun detailTv(tvShowId: Int): LiveData<TvEntity> =
        movieRepository.getDetailTv(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        movieRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvEntity){
        movieRepository.setFavoriteTvShow(tvShow)
    }
}