package com.nutitionwallah.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nutritionwallah.shared.BorderIdle
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.SurfaceLighter
import com.nutritionwallah.shared.TextPrimary
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AlertTextField(
    modifier: Modifier = Modifier,
    text:String,
    icon : DrawableResource? = null,
    onClick:()->Unit,

){
    Row (
        modifier=modifier
            .background(SurfaceLighter)
            .border(
                width = 1.dp,
                color = BorderIdle,
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() }
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(icon!=null){
            Image(
                modifier = Modifier.size(14.dp),
                painter = painterResource(icon),
                contentDescription = "Alert Icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            fontSize = FontSize.REGULAR,
            color = TextPrimary
        )
    }

}