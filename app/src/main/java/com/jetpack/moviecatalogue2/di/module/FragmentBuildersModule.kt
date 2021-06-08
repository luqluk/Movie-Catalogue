package com.jetpack.moviecatalogue2.di.module


import com.jetpack.moviecatalogue2.ui.main.content.favorite.FavoriteFragment
import com.jetpack.moviecatalogue2.ui.main.content.favorite.FavoriteMovieFragment
import com.jetpack.moviecatalogue2.ui.main.content.favorite.FavoriteTvShowFragment
import com.jetpack.moviecatalogue2.ui.main.content.movie.MovieFragment
import com.jetpack.moviecatalogue2.ui.main.content.tv.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment?

    @ContributesAndroidInjector
    abstract fun contributeTvFragment(): TvShowFragment?

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment?

    @ContributesAndroidInjector
    abstract fun contributeMovieFavFragment(): FavoriteMovieFragment?

    @ContributesAndroidInjector
    abstract fun contributeTvFavFragment(): FavoriteTvShowFragment?
}