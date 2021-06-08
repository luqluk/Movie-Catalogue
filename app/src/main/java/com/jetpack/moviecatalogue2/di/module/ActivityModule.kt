package com.jetpack.moviecatalogue2.di.module


import com.jetpack.moviecatalogue2.ui.detail.DetailActivity
import com.jetpack.moviecatalogue2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}