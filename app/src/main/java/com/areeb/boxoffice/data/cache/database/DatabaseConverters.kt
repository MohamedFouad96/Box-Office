package com.areeb.boxoffice.data.cache.database

import androidx.room.TypeConverter
import com.areeb.boxoffice.data.cache.entity.MovieType

class DatabaseConverters {
    @TypeConverter
    fun fromMovieType(value: String?): MovieType? {
        return MovieType.fromValue(value)
    }

    @TypeConverter
    fun dateToString(type: MovieType?): String? {
        return type?.value
    }
}