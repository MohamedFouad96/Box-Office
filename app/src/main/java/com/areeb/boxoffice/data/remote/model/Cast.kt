package com.areeb.boxoffice.data.remote.model


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("profile_path")
    val profilePath: String?
)