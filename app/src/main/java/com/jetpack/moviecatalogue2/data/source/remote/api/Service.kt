package com.jetpack.moviecatalogue2.data.source.remote.api

import com.jetpack.moviecatalogue2.data.source.remote.response.Responses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String
    ): Call<Responses>

    @GET("tv/airing_today")
    fun getTvShows(@Query("api_key") apiKey: String?): Call<Responses>

}