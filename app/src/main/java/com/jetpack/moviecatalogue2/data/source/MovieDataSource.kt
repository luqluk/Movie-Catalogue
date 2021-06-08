package com.jetpack.moviecatalogue2.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.remote.response.MovieResponse
import com.jetpack.moviecatalogue2.vo.Resource

interface MovieDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getTvShows(): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailTv(tvId: Int): LiveData<TvEntity>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTvShow(tvShow: TvEntity)
}