package com.nutritionwallah.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nutritionwallah.auth.AuthScreen
import com.nutritionwallah.shared.navigation.Screen
import com.nutritiwallah.home.HomeGraphScreen

@Composable
fun SetUpNavGraph(startDestination: Screen = Screen.Auth){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable<Screen.Auth>{
            AuthScreen(
                navigateToHomeGraph = {
                    navController.navigate(Screen.HomeGraph){
                        popUpTo<Screen.Auth>{
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screen.HomeGraph>{
            HomeGraphScreen()
        }
    }
}