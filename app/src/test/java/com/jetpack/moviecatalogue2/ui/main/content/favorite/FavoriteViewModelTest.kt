package com.jetpack.moviecatalogue2.ui.main.content.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun `get favorite movies`() {
        val dummyList = moviePagedList
        `when`(dummyList.size).thenReturn(5)
        val expectation = MutableLiveData<PagedList<MovieEntity>>()
        expectation.value = dummyList

        `when`(movieRepository.getFavoriteMovies()).thenReturn(expectation)

        val favMovies = viewModel.getFavoriteMovies().value
        verify(movieRepository).getFavoriteMovies()

        assertNotNull(favMovies)
        assertEquals(dummyList.size, favMovies?.size)

        viewModel.getFavoriteMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyList)
    }

    @Test
    fun `get favorite tv`() {
        val dummyList = tvShowPagedList
        `when`(dummyList.size).thenReturn(5)
        val expectation = MutableLiveData<PagedList<TvEntity>>()
        expectation.value = dummyList

        `when`(movieRepository.getFavoriteTvShows()).thenReturn(expectation)

        val favMovies = viewModel.getFavoriteTvShows().value
        verify(movieRepository).getFavoriteTvShows()

        assertNotNull(favMovies)
        assertEquals(dummyList.size, favMovies?.size)

        viewModel.getFavoriteTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyList)
    }

}
