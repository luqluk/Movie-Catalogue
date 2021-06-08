package com.jetpack.moviecatalogue2.ui.main.content.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies() : LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getMovies()

    fun getShows():  LiveData<Resource<PagedList<TvEntity>>>  = movieRepository.getTvShows()

}