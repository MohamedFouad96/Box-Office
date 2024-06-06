package com.areeb.boxoffice.ui.main.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.areeb.boxoffice.data.model.MovieCast
import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.data.model.MovieReview
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repo: IMovieRepository): BaseViewModel() {


    private val _movieDetails = mutableStateOf<MovieDetails?>(null)
    val movieDetails: State<MovieDetails?> = _movieDetails

    private val _movieReviews = mutableStateOf<List<MovieReview>?>(null)
    val movieReviews: State<List<MovieReview>?> = _movieReviews

    private val _movieCast = mutableStateOf<List<MovieCast>?>(null)
    val movieCast: State<List<MovieCast>?> = _movieCast


    private val _isBookmarked = mutableStateOf<Boolean?>(null)
    val isBookmarked: State<Boolean?> = _isBookmarked


    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState


    fun getMovieDetails(movieId: Int) {


        viewModelScope.launch(Dispatchers.IO) {

            repo.getMovieDetails(movieId).collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        showLoading.value = false
                        _movieDetails.value = it.data
                    }

                    Resource.Status.LOADING -> {
                        showLoading.value = true
                    }
                    Resource.Status.ERROR -> {
                        showLoading.value = false
                        it.message?.let { message ->
                            _errorState.value = message
                        }
                    }

                }
            }
        }
    }


    fun fetchMovieReviews(movieId: Int) {


        viewModelScope.launch(Dispatchers.IO) {

            repo.getMovieReviews(movieId).collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        showLoading.value = false
                        _movieReviews.value = it.data
                    }

                    Resource.Status.LOADING -> {
                        showLoading.value = true
                    }
                    Resource.Status.ERROR -> {
                        showLoading.value = false
                        it.message?.let { message ->
                            _errorState.value = message
                        }
                    }

                }
            }
        }
    }

    fun fetchMovieCast(movieId: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            repo.getMovieCast(movieId).collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        showLoading.value = false
                        _movieCast.value = it.data
                    }

                    Resource.Status.LOADING -> {
                        showLoading.value = true
                    }
                    Resource.Status.ERROR -> {
                        showLoading.value = false
                        it.message?.let { message ->
                            _errorState.value = message
                        }
                    }

                }
            }
        }
    }



    fun fetchMovieIsInWatchList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.isMovieBookmarked(movieId).collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        showLoading.value = false
                        _isBookmarked.value = it.data
                    }

                    Resource.Status.LOADING -> {
                        showLoading.value = true
                    }
                    Resource.Status.ERROR -> {
                        showLoading.value = false
                        it.message?.let { message ->
                            _errorState.value = message
                        }
                    }

                }
            }
        }
    }



    fun changeMovieBookmark(movieId: Int, isBookmarked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.changeMovieBookmark(movieId, isBookmarked)
            _isBookmarked.value = isBookmarked
        }
    }



}