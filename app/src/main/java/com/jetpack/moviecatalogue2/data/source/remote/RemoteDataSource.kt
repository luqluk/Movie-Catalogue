package com.jetpack.moviecatalogue2.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.moviecatalogue2.BuildConfig.API_KEY
import com.jetpack.moviecatalogue2.data.source.remote.api.Service
import com.jetpack.moviecatalogue2.data.source.remote.response.Responses
import com.jetpack.moviecatalogue2.utils.ErrorMessageHandler
import com.jetpack.moviecatalogue2.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: Service) {

    fun getMovies(): LiveData<ApiResponse<Responses>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Responses>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getMovies(API_KEY).await()
                if (response.results.isEmpty()) {
                    result.postValue(ApiResponse.empty("No Data Found", response))
                } else {
                    result.postValue(ApiResponse.success(response))
                }
            } catch (e: Exception) {
                result.postValue(ApiResponse.error(ErrorMessageHandler.generateErrorMessage(e)))
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

    fun getTvShows(): LiveData<ApiResponse<Responses>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Responses>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getTvShows(API_KEY).await()
                if (response.results.isEmpty()) {
                    result.postValue(ApiResponse.empty("No Data Found", response))
                } else {
                    result.postValue(ApiResponse.success(response))
                }
            } catch (e: Exception) {
                result.postValue(ApiResponse.error(ErrorMessageHandler.generateErrorMessage(e)))
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }
}