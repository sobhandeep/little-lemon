package com.example.littlelemon.navgraph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.littlelemon.HOME_ROUTE
import com.example.littlelemon.ONBOARDING_ROUTE

@Composable
fun SetupNavGraph(context: Context, navController: NavHostController){
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var startDestination = ONBOARDING_ROUTE
    if(sharedPreferences.getBoolean("userRegistered", false)){
        startDestination = HOME_ROUTE
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        onboardingNav(context, navController)
        homeNav(navController)
        profileNav(context, navController)

    }
}