package com.areeb.boxoffice.data.di



import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.repo.HomeRepository
import com.areeb.boxoffice.repo.IHomeRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.repo.MovieRepository
import org.koin.dsl.module


val repoModule = module {
    single { createHomeRepo(get(), get(), get()) }
    single { createMovieRepo(get(), get()) }
}

fun createHomeRepo(api: BoxOfficeApi, moviesDao: MoviesDao, remoteDao: RemoteKeysDao): IHomeRepository {
    return HomeRepository(api,moviesDao, remoteDao)
}

fun createMovieRepo(api: BoxOfficeApi, moviesDao: MoviesDao): IMovieRepository {
    return MovieRepository(api,moviesDao)
}