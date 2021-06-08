package com.jetpack.moviecatalogue2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {


    private val dummyMovie = DataDummy.generateLocalDummyMovies()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummy.generateLocalDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun `get movie detail`() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val result = viewModel.detailMovie(movieId)
        result.observeForever(observerMovie)
        verify(movieRepository).getDetailMovie(movieId)

        assertNotNull(result)
        assertEquals(dummyMovie.movieId, result.value?.movieId)
        observerMovie.onChanged(dummyMovie)

    }

    @Test
    fun `get tv detail`() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = dummyTvShow

        `when`(movieRepository.getDetailTv(tvShowId)).thenReturn(tvDummy)

        val result = viewModel.detailTv(tvShowId)
        result.observeForever(observerTvShow)
        verify(movieRepository).getDetailTv(tvShowId)

        assertNotNull(result)
        assertEquals(dummyTvShow.tvId, result.value?.tvId)
        observerTvShow.onChanged(dummyTvShow)

    }

    @Test
    fun `set favorite movie`() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val result = viewModel.detailMovie(movieId)
        result.observeForever(observerMovie)
        verify(movieRepository).getDetailMovie(movieId)

        doNothing().`when`(movieRepository).setFavoriteMovie(dummyMovie)
        viewModel.setFavoriteMovie(dummyMovie)
        verify(movieRepository).setFavoriteMovie(dummyMovie)
    }

    @Test
    fun `set favorite tv`() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = dummyTvShow

        `when`(movieRepository.getDetailTv(tvShowId)).thenReturn(tvDummy)

        val result = viewModel.detailTv(tvShowId)
        result.observeForever(observerTvShow)
        verify(movieRepository).getDetailTv(tvShowId)

        doNothing().`when`(movieRepository).setFavoriteTvShow(dummyTvShow)
        viewModel.setFavoriteTvShow(dummyTvShow)
        verify(movieRepository).setFavoriteTvShow(dummyTvShow)
    }
}