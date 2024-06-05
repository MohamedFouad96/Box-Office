package com.areeb.boxoffice.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieReviewResult(
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @SerializedName("content")
    val content: String
)