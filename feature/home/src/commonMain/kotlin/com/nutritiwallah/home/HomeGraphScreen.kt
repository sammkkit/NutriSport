package com.nutritiwallah.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nutritionwallah.shared.BebasNeueFont
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.IconPrimary
import com.nutritionwallah.shared.Resource
import com.nutritionwallah.shared.RobotoCondensedFont
import com.nutritionwallah.shared.Surface
import com.nutritionwallah.shared.TextPrimary
import com.nutritionwallah.shared.navigation.Screen
import com.nutritiwallah.home.component.BottomBar
import com.nutritiwallah.home.domain.BottomBarDestination
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(

) {
    val navController = rememberNavController()
    val currentRouteState = navController.currentBackStackEntryAsState()

    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRouteState.value?.destination?.route.toString()
            when{
                route.contains(BottomBarDestination.ProductsOverview.screen.toString()) -> BottomBarDestination.ProductsOverview
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.ProductsOverview
            }
        }
    }
    Scaffold (
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                title= {
                    AnimatedContent(
                        targetState = selectedDestination
                    ) {
                        Text(
                            text = it.title.uppercase(),
                            color = TextPrimary,
                            fontSize = FontSize.LARGE,
                            fontFamily = BebasNeueFont(),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}){
                        Icon(
                            painter = painterResource(Resource.Icon.Menu),
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        }
    ){padding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {

           NavHost(
               modifier = Modifier.weight(1f),
                navController=navController,
               startDestination = Screen.ProductsOverview
            ){
                composable<Screen.ProductsOverview>{

                }
               composable<Screen.Cart>{

               }
               composable<Screen.Categories>{

               }
           }
            Spacer(modifier = Modifier.height(12.dp) )
            Box(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                BottomBar(
                    selected = selectedDestination,
                    onSelect = {
                        navController.navigate(it.screen){
                            launchSingleTop = true
                            popUpTo<Screen.ProductsOverview> {
                                saveState = true
                                inclusive = false
                            }
                            restoreState = false
                        }
                    },
                )
            }

        }

    }
}