package com.areeb.boxoffice.ui.main.tabs.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.repo.IHomeRepository
import com.areeb.boxoffice.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: IHomeRepository): BaseViewModel() {


    private val _popularMovies = mutableStateOf<List<Movie>?>(null)
    val popularMovies: State<List<Movie>?> = _popularMovies

    private val _nowPlayingMovies = mutableStateOf<List<Movie>?>(null)
    val nowPlayingMovies: State<List<Movie>?> = _nowPlayingMovies

    private val _upcomingMovies = mutableStateOf<List<Movie>?>(null)
    val upcomingMovies: State<List<Movie>?> = _upcomingMovies

    private val _topRatedMovies = mutableStateOf<List<Movie>?>(null)
    val topRatedMovies: State<List<Movie>?> = _topRatedMovies


    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState



    fun fetchMoviesByType(type: MovieType, firstPage: Boolean = true) {

        if (firstPage && firstPageCheck(type))
            return


        viewModelScope.launch(Dispatchers.IO) {

            repo.getMoviesByType(firstPage,type).collectLatest {  resource ->

                showLoading.value = (resource.status == Resource.Status.LOADING)


                when (resource.status) {
                    Resource.Status.SUCCESS, Resource.Status.LOADING -> {
                        resource.data?.let { movies ->
                            when(type) {
                                MovieType.NowPlaying ->  {
                                    if (movies.isNotEmpty() && movies != _nowPlayingMovies.value)
                                        _nowPlayingMovies.value = resource.data
                                }
                                MovieType.Popular -> {
                                    if (movies.isNotEmpty() && movies != _popularMovies.value)
                                        _popularMovies.value = resource.data
                                }
                                MovieType.Upcoming -> {
                                    if (movies.isNotEmpty() && movies != _upcomingMovies.value)
                                        _upcomingMovies.value = resource.data
                                }
                                MovieType.TopRated -> {}
                            }
                        }
                    }
                        Resource.Status.ERROR -> {
                        resource.message?.let { message ->

                            when(type) {
                                MovieType.NowPlaying ->  {
                                    if (!_nowPlayingMovies.value.isNullOrEmpty())
                                        showSnackBarError.emit(message)
                                    else
                                        _errorState.value = message
                                }
                                MovieType.Popular -> {
                                    if (!_popularMovies.value.isNullOrEmpty())
                                        showSnackBarError.emit(message)
                                    else
                                        _errorState.value = message
                                }
                                MovieType.Upcoming -> {
                                    if (!_upcomingMovies.value.isNullOrEmpty())
                                        showSnackBarError.emit(message)
                                    else
                                        _errorState.value = message
                                }
                                MovieType.TopRated -> {}
                            }


                        }
                        showLoading.value = false

                    }

                }
            }
        }
    }


    fun fetchTopRatedMovies() {


        viewModelScope.launch(Dispatchers.IO) {

            repo.getTopRatedMovies().collectLatest {
                showLoading.value = (it.status == Resource.Status.LOADING)

                when (it.status) {
                    Resource.Status.SUCCESS, Resource.Status.LOADING -> {
                        it.data?.let { movies ->
                            if (movies.isNotEmpty() && movies != _topRatedMovies.value)
                                _topRatedMovies.value = movies
                        }
                    }
                    Resource.Status.ERROR -> {
                        it.message?.let { message ->
                            if (!_topRatedMovies.value.isNullOrEmpty())
                                showSnackBarError.emit(message)
                           else
                               _errorState.value = message
                        }
                    }

                }
            }
        }
    }


    private fun firstPageCheck(type: MovieType)  =
        when(type) {
            MovieType.NowPlaying -> _nowPlayingMovies.value?.isNotEmpty()
            MovieType.Popular -> _popularMovies.value?.isNotEmpty()
            MovieType.Upcoming -> _upcomingMovies.value?.isNotEmpty()
            MovieType.TopRated -> _topRatedMovies.value?.isNotEmpty()
        } ?: false

}