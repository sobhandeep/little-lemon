package com.example.littlelemon.navgraph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.littlelemon.ONBOARDING_ROUTE
import com.example.littlelemon.Screen
import com.example.littlelemon.screens.OnboardingScreen

fun NavGraphBuilder.onboardingNav(context: Context, navController: NavHostController){
    navigation(
        startDestination = Screen.Onboard.route,
        route = ONBOARDING_ROUTE
    ){
        composable(route = Screen.Onboard.route) {
            OnboardingScreen(context, navController)
        }
    }
}