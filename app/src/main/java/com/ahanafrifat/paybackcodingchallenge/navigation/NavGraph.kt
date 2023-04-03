package com.ahanafrifat.paybackcodingchallenge.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahanafrifat.paybackcodingchallenge.presentation.detailsScreen.DetailScreen
import com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen.HomeScreen
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.DETAILS_HIT_ID

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    )
    {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(DETAILS_HIT_ID) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(navController = navController)
        }
    }
}