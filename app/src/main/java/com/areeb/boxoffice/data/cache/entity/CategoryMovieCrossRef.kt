package com.areeb.boxoffice.data.cache.entity

import androidx.room.Entity


@Entity(primaryKeys = ["type", "id"])
data class CategoryMovieCrossRef(
    val type: MovieType,
    val id: Int
)
