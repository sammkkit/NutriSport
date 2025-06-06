package com.nutritiwallah.home.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nutritionwallah.shared.BebasNeueFont
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.Resource
import com.nutritionwallah.shared.TextPrimary
import com.nutritionwallah.shared.TextSecondary
import com.nutritiwallah.home.domain.DrawerItem
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomDrawer(
    onProfileClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {},
    onAdminPanelClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "NUTRISPORT",
            textAlign = TextAlign.Center,
            color = TextSecondary,
            fontFamily = BebasNeueFont(),
            fontSize = FontSize.EXTRA_LARGE
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Healthy Lifestyle",
            textAlign = TextAlign.Center,
            color = TextPrimary,
            fontSize = FontSize.REGULAR
        )
        Spacer(modifier = Modifier.height(50.dp))
        DrawerItem.entries.take(5).forEach { item ->
            DrawerItemCard(
                drawerItem = item,
                onClick = {
                    when (item) {
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.Contact -> onContactUsClick()
                        DrawerItem.SignOut -> onSignOutClick()
                        else -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        DrawerItemCard(
            drawerItem = DrawerItem.Admin,
            onClick = onAdminPanelClick
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}