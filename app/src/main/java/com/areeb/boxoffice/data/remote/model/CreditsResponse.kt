package com.areeb.boxoffice.data.remote.model


import com.areeb.boxoffice.data.model.MovieCast
import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast>
)

fun CreditsResponse.toDomain() = cast.map { MovieCast(it.originalName,"https://image.tmdb.org/t/p/w500${it.profilePath}") }