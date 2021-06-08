package com.jetpack.moviecatalogue2.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jetpack.moviecatalogue2.data.source.local.LocalDataSource
import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.remote.ApiResponse
import com.jetpack.moviecatalogue2.data.source.remote.NetworkBoundResource
import com.jetpack.moviecatalogue2.data.source.remote.RemoteDataSource
import com.jetpack.moviecatalogue2.data.source.remote.response.Responses
import com.jetpack.moviecatalogue2.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    MovieDataSource {
    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, Responses>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?) = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<Responses>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: Responses) {
                val movieList = ArrayList<MovieEntity>()
                data.results.forEach { movie ->
                    val entity = MovieEntity(
                        movie.id,
                        movie.title,
                        movie.titleShow,
                        movie.rate,
                        movie.poster,
                        movie.language,
                        movie.vote,
                        movie.overview,
                    )
                    movieList.add(entity)
                    localDataSource.insertMovies(movieList)
                }
            }
        }.asLiveData()
    }


    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(movieId)

    override fun getTvShows(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, Responses>() {

            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }


            override fun shouldFetch(data: PagedList<TvEntity>?) = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<Responses>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: Responses) {
                val tvList = ArrayList<TvEntity>()
                data.results.forEach { movie ->
                    val entity = TvEntity(
                        movie.id,
                        movie.title,
                        movie.titleShow,
                        movie.rate,
                        movie.poster,
                        movie.language,
                        movie.vote,
                        movie.overview,
                    )
                    tvList.add(entity)
                    localDataSource.insertTvShows(tvList)
                }
            }
        }.asLiveData()
    }


    override fun getDetailTv(tvId: Int): LiveData<TvEntity> =
        localDataSource.getDetailTvShow(tvId)

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {

        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }


}