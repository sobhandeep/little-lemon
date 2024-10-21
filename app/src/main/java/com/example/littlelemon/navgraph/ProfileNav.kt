package com.example.littlelemon.navgraph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.littlelemon.PROFILE_ROUTE
import com.example.littlelemon.Screen
import com.example.littlelemon.screens.ProfileScreen

fun NavGraphBuilder.profileNav(context: Context, navController: NavHostController) {
    navigation(
        startDestination = Screen.Profile.route,
        route = PROFILE_ROUTE
    ) {
        composable(route = Screen.Profile.route) {
            ProfileScreen(context, navController)
        }
    }
}