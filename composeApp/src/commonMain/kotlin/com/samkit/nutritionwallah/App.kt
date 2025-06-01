package com.samkit.nutritionwallah

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.nutritionwallah.navigation.SetUpNavGraph
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.nutritionwallah.data.domain.CustomerRepository
import com.nutritionwallah.shared.navigation.Screen
import com.nutritionwallah.shared.Constants
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        var appReady by remember { mutableStateOf(false) }
        val customerRepository = koinInject<CustomerRepository>()
        val isUserAuthenticated = customerRepository.getCurrentUserId() != null
        var startDestination = remember { (if (isUserAuthenticated) Screen.HomeGraph else Screen.Auth) }

        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(serverId = Constants.WEB_CLIENT_ID),
            )
            appReady = true
        }
        AnimatedVisibility(appReady) {
            SetUpNavGraph(startDestination)
        }
    }
}