package com.nutritiwallah.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nutritionwallah.shared.IconPrimary
import com.nutritionwallah.shared.IconSecondary
import com.nutritionwallah.shared.Surface
import com.nutritionwallah.shared.SurfaceLighter
import com.nutritiwallah.home.domain.BottomBarDestination
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(
    modifier:Modifier = Modifier,
    selected :BottomBarDestination,
    onSelect:(BottomBarDestination)->Unit
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceLighter)
            .padding(vertical = 12.dp, horizontal = 36.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        BottomBarDestination.entries.forEach {
            val animatedTint by animateColorAsState(
                targetValue = if(selected == it) IconSecondary else IconPrimary
            )
            IconButton(
                onClick = { onSelect(it) }
            ){
                Icon(
                    painter = painterResource(it.icon),
                    contentDescription = it.title,
                    tint = animatedTint
                )
            }
        }

    }

}