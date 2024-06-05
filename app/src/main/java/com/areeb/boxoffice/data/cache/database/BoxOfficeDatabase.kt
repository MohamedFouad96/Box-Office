package com.areeb.boxoffice.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.CategoryEntity
import com.areeb.boxoffice.data.cache.entity.CategoryMovieCrossRef
import com.areeb.boxoffice.data.cache.entity.RemoteKeys
import com.areeb.boxoffice.data.cache.entity.MovieEntity

@Database(entities = [ MovieEntity::class, CategoryEntity::class , RemoteKeys::class, CategoryMovieCrossRef::class ] , version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class BoxOfficeDatabase : RoomDatabase() {

    abstract fun moviesDao() : MoviesDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        const val DATABASE_NAME = "Box_Office_db"
    }

}

