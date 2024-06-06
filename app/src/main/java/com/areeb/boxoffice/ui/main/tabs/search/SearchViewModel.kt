package com.areeb.boxoffice.ui.main.tabs.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: IMovieRepository): BaseViewModel() {


    private val _movies = mutableStateOf<List<Movie>?>(null)
    val movies: State<List<Movie>?> = _movies

    private val _searchTextIsError = mutableStateOf(false)
    val searchTextIsError: State<Boolean> = _searchTextIsError


    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState



    fun searchForMovies(query: String) {

        _searchTextIsError.value = query.isEmpty()

        if (query.isEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {

            repo.searchForMovies(query).collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        showLoading.value = false
                        _movies.value = it.data

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




}