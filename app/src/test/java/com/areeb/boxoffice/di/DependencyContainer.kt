package com.areeb.boxoffice.di


import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.repo.HomeRepository
import com.areeb.boxoffice.repo.IHomeRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.repo.MovieRepository
import com.areeb.boxoffice.data.util.CacheState
import com.areeb.boxoffice.data.util.NetworkState
import com.areeb.boxoffice.data.cache.FakeMoviesDao
import com.areeb.boxoffice.data.cache.FakeRemoteKeyDao
import com.areeb.boxoffice.data.remote.FakeBoxOfficeApi


class DependencyContainer {

    lateinit var fakeBoxOfficeApi: BoxOfficeApi



    lateinit var fakeMoviesDao: MoviesDao
    lateinit var fakeRemoteKeys: RemoteKeysDao



    lateinit var homeRepo: IHomeRepository
    lateinit var movieRepo: IMovieRepository





    fun build() {

        fakeBoxOfficeApi = FakeBoxOfficeApi()

        fakeMoviesDao = FakeMoviesDao()
        fakeRemoteKeys = FakeRemoteKeyDao()


        homeRepo = HomeRepository(fakeBoxOfficeApi,fakeMoviesDao,fakeRemoteKeys)
        movieRepo = MovieRepository(fakeBoxOfficeApi,fakeMoviesDao)


    }


    fun setWebServiceStateTo(state: NetworkState) {
        (fakeBoxOfficeApi as FakeBoxOfficeApi).state = state
    }

    fun setCacheStateTo(state: CacheState) {
        (fakeMoviesDao as FakeMoviesDao).state = state
    }


}