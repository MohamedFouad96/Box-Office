package com.areeb.boxoffice.data.remote.model


import com.google.gson.annotations.SerializedName
import com.areeb.boxoffice.data.model.MovieReview

data class MovieReviewsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieReviewResult>,
    @SerializedName("total_pages")
    val totalPages: Int
)

fun MovieReviewsResponse.toDomain() = results.map { MovieReview(it.content,it.authorDetails.username, "https://image.tmdb.org/t/p/w500${it.authorDetails.avatarPath}",it.authorDetails.rating) }