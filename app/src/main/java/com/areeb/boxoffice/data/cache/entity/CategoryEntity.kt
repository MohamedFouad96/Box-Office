package com.areeb.boxoffice.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey
    val type: MovieType
)


