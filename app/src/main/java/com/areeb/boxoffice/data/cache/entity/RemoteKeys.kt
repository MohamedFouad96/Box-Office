package com.areeb.boxoffice.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    var nextKey: Int?,
    val isEndReached: Boolean = false,
    var prevKey: Int? = 1,
    val type: String = "default"
)