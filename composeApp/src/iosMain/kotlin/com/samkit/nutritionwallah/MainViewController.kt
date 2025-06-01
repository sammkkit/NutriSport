package com.samkit.nutritionwallah

import androidx.compose.ui.window.ComposeUIViewController
import com.nutritionwallah.di.iniKoin

fun MainViewController() = ComposeUIViewController (
    configure = { iniKoin() }
){ App() }