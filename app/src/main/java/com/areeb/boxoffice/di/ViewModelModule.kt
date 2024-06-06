package com.areeb.boxoffice.di


import com.areeb.boxoffice.repo.IHomeRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.ui.main.screens.MovieDetailsViewModel
import com.areeb.boxoffice.ui.main.tabs.home.HomeViewModel
import com.areeb.boxoffice.ui.main.tabs.search.SearchViewModel
import com.areeb.boxoffice.ui.main.tabs.watch_list.WatchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { createHomeViewModel(get()) }
    viewModel { createSearchViewModel(get()) }
    viewModel { createMovieDetailsViewModel(get()) }
    viewModel { createWatchListViewModel(get()) }

}

fun createHomeViewModel(repo: IHomeRepository): HomeViewModel {
    return HomeViewModel(repo)
}

fun createSearchViewModel(repo: IMovieRepository): SearchViewModel {
    return SearchViewModel(repo)
}

fun createMovieDetailsViewModel(repo: IMovieRepository): MovieDetailsViewModel {
    return MovieDetailsViewModel(repo)
}

fun createWatchListViewModel(repo: IMovieRepository): WatchListViewModel {
    return WatchListViewModel(repo)
}
