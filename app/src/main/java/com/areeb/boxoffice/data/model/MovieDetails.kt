package com.areeb.boxoffice.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: String,
    val voteAverage: Double,
    val genre: String?,
): Parcelable