package com.jetpack.moviecatalogue2.di.module

import android.app.Application
import com.jetpack.moviecatalogue2.data.source.MovieRepository
import com.jetpack.moviecatalogue2.data.source.local.LocalDataSource
import com.jetpack.moviecatalogue2.data.source.local.room.MovieDao
import com.jetpack.moviecatalogue2.data.source.local.room.MovieDatabase
import com.jetpack.moviecatalogue2.data.source.remote.RemoteDataSource
import com.jetpack.moviecatalogue2.data.source.remote.api.Service
import com.jetpack.moviecatalogue2.viewModel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideCatalogDatabase(application: Application): MovieDatabase =
            MovieDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideCatalogDao(movieDatabase: MovieDatabase): MovieDao =
            movieDatabase.movieDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource =
            LocalDataSource(movieDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: Service): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): MovieRepository = MovieRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(movieRepository: MovieRepository): ViewModelFactory =
            ViewModelFactory(movieRepository)

    }
}