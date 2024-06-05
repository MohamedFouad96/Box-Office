package com.areeb.boxoffice.data.remote.model


import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String?,
    @SerializedName("username")
    val username: String,
    @SerializedName("rating")
    val rating: Int
)