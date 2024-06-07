package com.areeb.boxoffice.ui.main.components

import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.navgraph.Route


data class BottomNavigationItem(
    val label : Int = R.string.home,
    val icon : Int = R.drawable.ic_home,
    val activeIcon: Int = R.drawable.ic_home_active,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = R.string.home,
                icon = R.drawable.ic_home,
                activeIcon = R.drawable.ic_home_active,
                route = Route.HomePage.route
            ),
            BottomNavigationItem(
                label = R.string.search,
                icon = R.drawable.ic_search,
                activeIcon = R.drawable.ic_search_active,
                route = Route.SearchPage.route
            ),
            BottomNavigationItem(
                label = R.string.watch_list,
                icon = R.drawable.ic_save,
                activeIcon = R.drawable.ic_save_active,
                route = Route.WatchListPage.route
            )
        )
    }
}