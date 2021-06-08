package com.jetpack.moviecatalogue2.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_favorite")
    fun getMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_favorite")
    fun getTvShows() : DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_favorite WHERE isFavorite = 1")
    fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_favorite WHERE isFavorite = 1")
    fun getFavoriteTvShows() : DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_favorite WHERE movieId = :movieId")
    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tv_favorite WHERE tvId = :tvShowId")
    fun getDetailTv(tvShowId: Int) : LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvEntity::class)
    fun insertTvShows(tvShows: List<TvEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvEntity::class)
    fun updateTvShow(tvShows: TvEntity)

}