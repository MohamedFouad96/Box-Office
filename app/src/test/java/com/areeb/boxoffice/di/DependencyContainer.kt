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
import com.areeb.boxoffice.di.fake.FakeHomeRepository
import com.areeb.boxoffice.di.fake.FakeMovieRepository
import com.areeb.boxoffice.ui.main.screens.MovieDetailsViewModel
import com.areeb.boxoffice.ui.main.tabs.home.HomeViewModel
import com.areeb.boxoffice.ui.main.tabs.search.SearchViewModel
import com.areeb.boxoffice.ui.main.tabs.watch_list.WatchListViewModel


class DependencyContainer {

    lateinit var fakeBoxOfficeApi: BoxOfficeApi



    lateinit var fakeMoviesDao: MoviesDao
    lateinit var fakeRemoteKeys: RemoteKeysDao


    lateinit var fakeHomeRepo: IHomeRepository
    lateinit var fakeMovieRepo: IMovieRepository



    lateinit var homeRepo: IHomeRepository
    lateinit var movieRepo: IMovieRepository



    lateinit var homeViewModel: HomeViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var watchListViewModel: WatchListViewModel
    lateinit var movieDetailsViewModel: MovieDetailsViewModel




    fun build() {

        fakeBoxOfficeApi = FakeBoxOfficeApi()

        fakeMoviesDao = FakeMoviesDao()
        fakeRemoteKeys = FakeRemoteKeyDao()

        fakeMovieRepo = FakeMovieRepository()
        fakeHomeRepo = FakeHomeRepository()


        homeRepo = HomeRepository(fakeBoxOfficeApi,fakeMoviesDao,fakeRemoteKeys)
        movieRepo = MovieRepository(fakeBoxOfficeApi,fakeMoviesDao)

        homeViewModel = HomeViewModel(fakeHomeRepo)
        searchViewModel = SearchViewModel(fakeMovieRepo)
        watchListViewModel = WatchListViewModel(fakeMovieRepo)
        movieDetailsViewModel = MovieDetailsViewModel(fakeMovieRepo)

    }


    fun setWebServiceStateTo(state: NetworkState) {
        (fakeBoxOfficeApi as FakeBoxOfficeApi).state = state
    }

    fun setCacheStateTo(state: CacheState) {
        (fakeMoviesDao as FakeMoviesDao).state = state
    }


}