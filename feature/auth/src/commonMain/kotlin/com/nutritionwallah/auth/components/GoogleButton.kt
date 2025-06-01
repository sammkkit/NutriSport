package com.nutritionwallah.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateBounds
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.Gray
import com.nutritionwallah.shared.GrayDarker
import com.nutritionwallah.shared.IconSecondary
import com.nutritionwallah.shared.Resource
import com.nutritionwallah.shared.Surface
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loading:Boolean = false,
    primaryText:String = "Sign in with Google",
    secondaryText:String = "Please wait...",
    onClicked:()->Unit,
    icon : DrawableResource = Resource.Image.GoogleLogo,
    shape: Shape = RoundedCornerShape(99.dp),
    borderColor : Color = GrayDarker,
    backGroundColor : Color = Gray,
    progressIndicatorColor : Color = IconSecondary
){
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(loading){
        buttonText = if(loading) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clip(shape)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickable(enabled = !loading) { onClicked() },
        color = backGroundColor,

    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .animateContentSize (
                    animationSpec = tween(200)
                ),
            verticalAlignment =  Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            AnimatedVisibility(!loading){
                Icon(
                    painter = painterResource(icon),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Google Logo",
                    tint = Color.Unspecified
                )
            }
            AnimatedVisibility(loading){
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = progressIndicatorColor,
                    strokeWidth = 2.dp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = buttonText,
                color = Color.Black,
                fontSize = FontSize.REGULAR,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}