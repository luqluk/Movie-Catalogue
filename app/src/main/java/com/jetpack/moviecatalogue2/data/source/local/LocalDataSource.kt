package com.jetpack.moviecatalogue2.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.local.room.MovieDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getMovies()

    fun getTvShows() : DataSource.Factory<Int, TvEntity> = movieDao.getTvShows()

    fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovies()

    fun getFavoriteTvShows() : DataSource.Factory<Int, TvEntity> = movieDao.getFavoriteTvShows()

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieDao.getDetailMovie(movieId)

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvEntity> = movieDao.getDetailTv(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvEntity>) = movieDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie : MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow : TvEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieDao.updateTvShow(tvShow)
    }
}