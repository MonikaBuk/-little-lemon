package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, context: Context, menuDao: MenuDao) {
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val hasUserData = !sharedPreferences.getString("email", "").isNullOrBlank()
    
    val startDestination = if (hasUserData) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            HomeScreen(navController, menuDao)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }
}
