package com.areeb.boxoffice.di


import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.database.BoxOfficeDatabase
import org.koin.dsl.module


val daoModule = module {
    single { createMoviesDao(get()) }
    single {  createRemoteDao(get()) }

}

fun createMoviesDao(database: BoxOfficeDatabase): MoviesDao {
    return database.moviesDao()
}

fun createRemoteDao(database: BoxOfficeDatabase): RemoteKeysDao {
    return database.remoteKeysDao()
}