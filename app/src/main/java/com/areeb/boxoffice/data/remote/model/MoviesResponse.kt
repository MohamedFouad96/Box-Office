package com.areeb.boxoffice.data.remote.model


import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.util.ext.toEEEMMMddDateFormat
import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResult>,
    @SerializedName("total_pages")
    val totalPages: Int
)

fun MoviesResponse.toDomain() = results.map { Movie(it.id,it.originalTitle,it.overview, "https://image.tmdb.org/t/p/w500${it.posterPath}", "https://image.tmdb.org/t/p/w500${it.backdropPath}", it.releaseDate.toEEEMMMddDateFormat(),it.voteAverage) }


fun MoviesResponse.toEntity() = results.map {
    MovieEntity(it.id,it.originalTitle,it.overview,it.posterPath,it.backdropPath, it.releaseDate.toEEEMMMddDateFormat(),it.voteAverage)
}