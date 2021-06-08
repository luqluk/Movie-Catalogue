package com.jetpack.moviecatalogue2.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

    @SerializedName("original_title")
    var title: String? = null,

    @SerializedName("name")
    var titleShow: String? = null,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("vote_average")
    val rate: Double? = null,

    @SerializedName("poster_path")
    val poster: String? = null,

    @SerializedName("original_language")
    val language: String? = null,

    @SerializedName("vote_count")
    val vote: Int = 0,

    @SerializedName("overview")
    val overview: String? = null
) : Parcelable
