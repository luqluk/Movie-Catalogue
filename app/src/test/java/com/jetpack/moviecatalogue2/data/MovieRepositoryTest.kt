package com.jetpack.moviecatalogue2.data

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jetpack.moviecatalogue2.PagedListUtils
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.LocalDataSource
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.remote.RemoteDataSource
import com.jetpack.moviecatalogue2.utils.DataDummy
import com.jetpack.moviecatalogue2.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var remote = mock(RemoteDataSource::class.java)
    private var local = mock(LocalDataSource::class.java)

    private lateinit var movieRepository: MovieRepository

    private val movieResponses = DataDummy.generateLocalDummyMovies()
    private val movieId = movieResponses[0].movieId

    private val tvResponses = DataDummy.generateLocalDummyTvShows()
    private val tvId = tvResponses[0].tvId

    private val movieResponse = DataDummy.generateLocalDummyMovies()[0]
    private val tvResponse = DataDummy.generateLocalDummyTvShows()[0]

    @Before
    fun init() {
        movieRepository = MovieRepository(remote, local)
    }


    @Test
    fun `get movies success`() {

        val data = DataDummy.generateRemoteDummyMovies()
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSource)
        movieRepository.getMovies()

        val result = Resource.success(PagedListUtils.mockPagedList(data))
        verify(local).getMovies()
        assertNotNull(result.data)
        assertEquals(data.size.toLong(), result.data?.size?.toLong())

    }


    @Test
    fun `get tv show list`() {
        val data = DataDummy.generateRemoteDummyTvShows()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getTvShows()

        val result = Resource.success(PagedListUtils.mockPagedList(data))
        verify(local).getTvShows()
        assertNotNull(result.data)
        assertEquals(data.size.toLong(), result.data?.size?.toLong())
    }

    @Test
    fun `get movie detail`() {
        val expectation = MutableLiveData<MovieEntity>()
        expectation.value = movieResponse
        `when`(local.getDetailMovie(movieId)).thenReturn(expectation)

        val result = movieRepository.getDetailMovie(movieId)
        verify(local).getDetailMovie(movieId)
        assertNotNull(result)
        assertEquals(movieId, result.value?.movieId)

    }

    @Test
    fun `get tv show detail`() {
        val expectation = MutableLiveData<TvEntity>()
        expectation.value = tvResponse
        `when`(local.getDetailTvShow(tvId)).thenReturn(expectation)

        val result = movieRepository.getDetailTv(tvId)
        assertNotNull(result)

        verify(local).getDetailTvShow(tvId)
    }


    @Test
    fun `get favorite movies list`() {
        val response = DataDummy.generateRemoteDummyMovies()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovies()

        val result = PagedListUtils.mockPagedList(response)
        verify(local).getFavoriteMovies()

        assertNotNull(result)
        assertEquals(response.size, result.size)
    }

    @Test
    fun `get favorite tv shows list`() {
        val response = DataDummy.generateRemoteDummyTvShows()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)

        movieRepository.getFavoriteTvShows()
        val result = PagedListUtils.mockPagedList(response)
        verify(local).getFavoriteTvShows()

        assertNotNull(result)
        assertEquals(response.size, result.size)
    }

    @Test
    fun `set favorite movies`() {
        movieRepository.setFavoriteMovie(movieResponse)
        verify(local, times(1)).setFavoriteMovie(movieResponse)
    }

    @Test
    fun `set favorite tv`() {
        movieRepository.setFavoriteTvShow(tvResponse)
        verify(local, times(1)).setFavoriteTvShow(tvResponse)
    }

}
