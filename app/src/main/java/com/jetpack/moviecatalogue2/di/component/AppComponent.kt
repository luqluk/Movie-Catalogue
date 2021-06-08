package com.jetpack.moviecatalogue2.di.component

import android.app.Application
import com.jetpack.moviecatalogue2.App
import com.jetpack.moviecatalogue2.di.module.ActivityModule
import com.jetpack.moviecatalogue2.di.module.AppModule
import com.jetpack.moviecatalogue2.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}