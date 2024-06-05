package com.areeb.boxoffice.data.remote.model


import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.data.util.ext.toEEEMMMddDateFormat
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("genres")
    val genres: List<Genres>,
)

fun MovieDetailsResponse.toDomain() = with(this) { MovieDetails(id,originalTitle,overview, "https://image.tmdb.org/t/p/w500$backdropPath", "https://image.tmdb.org/t/p/w500$posterPath", releaseDate.toEEEMMMddDateFormat(), "$runtime minutes", voteAverage, genres.firstOrNull()?.name) }


