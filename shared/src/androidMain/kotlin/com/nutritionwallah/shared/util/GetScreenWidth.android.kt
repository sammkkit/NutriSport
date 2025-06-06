package com.nutritionwallah.shared.util

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

actual fun getScreenWidth(): Float {
    val config = Resources.getSystem()
    return config.displayMetrics.widthPixels/config.displayMetrics.density
}