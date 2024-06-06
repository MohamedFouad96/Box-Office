package com.areeb.boxoffice.ui.main.tabs.watch_list

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

class WatchListViewModel(private val repo: IMovieRepository): BaseViewModel() {


    private val _movies = mutableStateOf<List<Movie>?>(null)
    val movies: State<List<Movie>?> = _movies


    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState



    fun getWatchListMovies() {


        viewModelScope.launch(Dispatchers.IO) {

            repo.getWatchListMovies().collectLatest {
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