package com.nutritiwallah.home

import ContentWithMessageBar
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.nutritionwallah.shared.SurfaceBrand
import com.nutritionwallah.shared.SurfaceError
import com.nutritionwallah.shared.TextPrimary
import com.nutritionwallah.shared.TextWhite
import com.nutritionwallah.shared.navigation.Screen
import com.nutritionwallah.shared.util.getScreenWidth
import com.nutritiwallah.home.component.BottomBar
import com.nutritiwallah.home.component.CustomDrawer
import com.nutritiwallah.home.domain.BottomBarDestination
import com.nutritiwallah.home.domain.CustomDrawerState
import com.nutritiwallah.home.domain.isOpened
import com.nutritiwallah.home.domain.toggle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(
    navigateToAuth: () -> Unit
) {
    val navController = rememberNavController()
    val currentRouteState = navController.currentBackStackEntryAsState()
    val homeGraphViewModel: HomeGraphViewModel = koinViewModel()
    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRouteState.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.ProductsOverview.screen.toString()) -> BottomBarDestination.ProductsOverview
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.ProductsOverview
            }
        }
    }

    var drawerState by remember { mutableStateOf(CustomDrawerState.closed) }
    val screenWidth = remember { getScreenWidth() }
    val offset by remember {
        derivedStateOf {
            (screenWidth / 1.5).dp
        }
    }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offset else 0.dp
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1.0f
    )

    val animatedClip by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 20.dp else 0.dp
    )
    var messageBarState = rememberMessageBarState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        if (drawerState.isOpened()) {
            CustomDrawer(
                onSignOutClick = {
                    homeGraphViewModel.signOut(
                        onSuccess = {
                            navigateToAuth()
                        },
                        onError = { message ->
                            messageBarState.addError(message)
                        }
                    )
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(animatedClip))
                .offset(x = animatedOffset)
                .scale(animatedScale)
                .shadow(elevation = 20.dp, shape = RoundedCornerShape(animatedClip))
        ) {
            Scaffold(
                modifier = Modifier.offset(
                    x = 0.dp
                ),
                containerColor = Surface,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
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
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState = drawerState.toggle()
                                }
                            }) {
                                Icon(
                                    painter = if (!drawerState.isOpened()) painterResource(
                                        Resource.Icon.Menu
                                    )
                                    else painterResource(Resource.Icon.Close),
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
                },
                bottomBar = {
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    ) {
                        BottomBar(
                            selected = selectedDestination,
                            onSelect = {
                                navController.navigate(it.screen) {
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
            ) { padding ->
                ContentWithMessageBar(
                    contentBackgroundColor = Surface,
                    modifier = Modifier.padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    ),
                    messageBarState = messageBarState,
                    errorMaxLines = 2,
                    errorContainerColor = SurfaceError,
                    errorContentColor = TextWhite,
                    successContainerColor = SurfaceBrand,
                    successContentColor = TextPrimary
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        NavHost(
                            modifier = Modifier.weight(1f),
                            navController = navController,
                            startDestination = Screen.ProductsOverview
                        ) {
                            composable<Screen.ProductsOverview> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Red),
                                    contentAlignment = Alignment.Center
                                ){
                                    Text("${selectedDestination.title}")
                                }
                            }
                            composable<Screen.Cart> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Gray),
                                    contentAlignment = Alignment.Center
                                ){
                                    Text("${selectedDestination.title}")
                                }
                            }
                            composable<Screen.Categories> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Green),
                                    contentAlignment = Alignment.Center
                                ){
                                    Text("${selectedDestination.title}")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
