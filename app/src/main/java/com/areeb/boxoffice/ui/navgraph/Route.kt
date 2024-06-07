package com.areeb.boxoffice.ui.navgraph

sealed class Route(val route: String) {

    data object None: Route(route = "none")

    data object MainNavigation: Route(route = "mainNavigation")

    data object MainScreenContent: Route(route = "mainScreenContent")

    data object HomePage: Route(route = "homePage")
    data object SearchPage: Route(route = "searchPage")
    data object WatchListPage: Route(route = "watchListPage")
    data object MovieDetailsScreen: Route(route = "movieDetailsScreen")


}