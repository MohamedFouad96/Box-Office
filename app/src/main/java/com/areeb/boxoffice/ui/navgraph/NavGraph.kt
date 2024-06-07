package com.areeb.boxoffice.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.areeb.boxoffice.ui.main.MainScreenContent
import com.areeb.boxoffice.ui.main.screens.MOVIE_ID_KEY
import com.areeb.boxoffice.ui.main.screens.MovieDetailsScreens


@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()


    NavHost(navController = navController , startDestination = Route.MainNavigation.route) {


        navigation(route = Route.MainNavigation.route ,startDestination = Route.MainScreenContent.route) {
            composable(route = Route.MainScreenContent.route) {
                MainScreenContent(navController)
            }

            composable(route = Route.MovieDetailsScreen.route) {

                navController.previousBackStackEntry?.savedStateHandle?.get<Int>(MOVIE_ID_KEY)
                    ?.let { movieId ->

                        MovieDetailsScreens(movieId = movieId, mainNavController = navController)
                    }

            }


        }
    }

}

 fun NavHostController.navigateToMovieDetailsScreen(movieId: Int) {
     currentBackStackEntry?.savedStateHandle?.set(MOVIE_ID_KEY, movieId)

     navigate(
        route = Route.MovieDetailsScreen.route
    )
}

fun NavHostController.navigateToBottomBarPage(route: String) {

    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

}
