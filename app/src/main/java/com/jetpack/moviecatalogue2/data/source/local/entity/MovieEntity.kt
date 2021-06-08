package com.jetpack.moviecatalogue2.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jetpack.moviecatalogue2.utils.Constant.TABLE_MOVIE
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLE_MOVIE)
@Parcelize
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int = 0,

    var title: String? = null,
    var titleShow: String? = null,
    val rate: Double? = null,
    val poster: String? = null,
    val language: String? = null,
    val vote: Int = 0,
    val overview: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable

