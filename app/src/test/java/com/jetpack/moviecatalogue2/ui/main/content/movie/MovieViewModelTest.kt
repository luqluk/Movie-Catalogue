package com.jetpack.moviecatalogue2.ui.main.content.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.vo.Resource
import com.jetpack.moviecatalogue2.vo.Status
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }


    @Test
    fun `get movie list`() {
        val dummyList = Resource.success(moviePagedList)
        val expectation = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expectation.value = dummyList
        `when`(movieRepository.getMovies()).thenReturn(expectation)

        val result = viewModel.getMovies()

        assertNotNull(result)
        assertEquals(dummyList.data?.size, result.value?.data?.size)
        assertEquals(expectation.value?.data, result.value?.data)

        result.observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyList)
        verify(movieRepository).getMovies()
    }

    @Test
    fun `get movie list empty`() {
        val errorMessage = "No Data Found"
        val dummyList = moviePagedList
        val value = Resource(Status.ERROR, dummyList, errorMessage)
        val expectation = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expectation.value = value
        `when`(movieRepository.getMovies()).thenReturn(expectation)

        val result = viewModel.getMovies()
        result.observeForever(observerMovie)
        verify(observerMovie).onChanged(value)
        verify(movieRepository).getMovies()

        assertEquals(expectation.value, result.value)
        assertEquals(expectation.value?.message, result.value?.message)
        assertEquals(expectation.value?.message, errorMessage)

    }

    @Test
    fun `get tv list`() {
        val dummyList = Resource.success(tvShowPagedList)
        val expectation = MutableLiveData<Resource<PagedList<TvEntity>>>()
        expectation.value = dummyList
        `when`(movieRepository.getTvShows()).thenReturn(expectation)

        val result = viewModel.getShows()

        assertNotNull(result)
        assertEquals(dummyList.data?.size, result.value?.data?.size)
        assertEquals(expectation.value?.data, result.value?.data)

        result.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyList)
        verify(movieRepository).getTvShows()
    }

    @Test
    fun `get tv list empty`() {
        val errorMessage = "No Data Found"
        val dummyList = tvShowPagedList
        val value = Resource(Status.ERROR, dummyList, errorMessage)
        val expectation = MutableLiveData<Resource<PagedList<TvEntity>>>()
        expectation.value = value
        `when`(movieRepository.getTvShows()).thenReturn(expectation)

        val result = viewModel.getShows()
        result.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(value)
        verify(movieRepository).getTvShows()

        assertEquals(expectation.value, result.value)
        assertEquals(expectation.value?.message, result.value?.message)
        assertEquals(expectation.value?.message, errorMessage)

    }

}